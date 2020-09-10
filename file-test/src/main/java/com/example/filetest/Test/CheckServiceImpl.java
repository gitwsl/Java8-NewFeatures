package com.example.filetest.Test;

import com.alibaba.fastjson.JSON;
import com.example.filetest.utils.CommonConsts;
import com.example.filetest.utils.ConfigUtils;
import com.example.filetest.utils.FileUtils;
import com.example.filetest.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liaobaohuang
 * @date 2020/9/2
 */
@Service
@Slf4j
public class CheckServiceImpl implements CheckService {
    @Resource
    private ConfigUtils configUtils;

    @Value("${unicom.checkFile.type_0}")
    private String type_0_content_key;

    @Value("${unicom.checkFile.type_1}")
    private String type_1_content_key;

    @Value("${unicom.checkFile.save.paramName}")
    private String paramFileName;

    @Override
    public Boolean checkAndUploadExcel(CheckDTO checkDto) {
        try {
            final List<CheckFileDTO> checkDtoFileList = checkDto.getFileList();
            if (checkDtoFileList == null) {
                return false;
            }
            final Integer type = checkDto.getType();
            String contentKey = type_0_content_key;
            if (type == 1) {
                contentKey = type_1_content_key;
            }
            final String currentNodeId = checkDto.getCurrentNodeId();
            final String workOrderId = checkDto.getWorkOrderId();

            // 设置单个字段保存（工单编辑）部分参数
            String url = configUtils.getUnicomSaveHosts() + "/api/v1/dosm_activiti/userTask/singleField/save?id=" + workOrderId + "&nodeId=" + currentNodeId + "&language=zh";
            Map<String, Object> params = new HashMap<>();
            params.put("workOrderId", workOrderId);
            params.put("nodeId", currentNodeId);
            Map<String, String> headers = new HashMap<>();
            headers.put("accountId", checkDto.getAccountId());
            headers.put("userId", checkDto.getUserId());

            String finalContentKey = contentKey;
            for (int i = 0; i < checkDtoFileList.size(); i++) {
                CheckFileDTO checkFileDTO = checkDtoFileList.get(i);
                String fileUrl = checkFileDTO.getUrl();
                String fileName = fileUrl.substring(fileUrl.lastIndexOf(File.separator));

                // 文件下载到本地
                if (!downloadFile(fileUrl)) {
                    log.error("downloadFile fail");
                    return false;
                }

                // 文件校验
                String checkFileUrl = configUtils.getUnicomHosts() + "/api/v2/verify/" + type + "/excel";
                String uploadResult = HttpUtil.upload(checkFileUrl, new File(configUtils.getUploadFilePath(), fileName), this.paramFileName, new HashMap<>());
                if (null == uploadResult) {
                    log.error("uploadResult is null");
                    return false;
                }
                // 校验结果转换并删除文件
                CheckResult checkResult = JSON.parseObject(uploadResult, CheckResult.class);
                new File(configUtils.getUploadFilePath(), fileName).delete();
                // 单个字段保存（工单编辑）添加content参数
                params.put("content", "{" + finalContentKey + ":\"<p>" + checkResult.getResult().getExecCode() + "</p>\"}");
                try {
                    HttpUtil.put(HttpClientBuilder.create().build(), url, params, headers);
                } catch (IOException e) {
                    log.error("save singleField error");
                    return false;
                }
            }
        } catch (Exception e) {
            log.error("method exception");
            return false;
        }
        return true;
    }

    private boolean downloadFile(String fileUrl) {
        File dir = new File(configUtils.getUploadFilePath());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        InputStream inputStream = HttpUtil.httpGetDownloadFile(fileUrl);
        FileUtils.input2file(inputStream, new File(configUtils.getUploadFilePath(), fileUrl.substring(fileUrl.lastIndexOf(File.separator))));

        String fileName = fileUrl.substring(fileUrl.lastIndexOf(File.separator));
        if (!new File(configUtils.getUploadFilePath(), fileName).exists()) {
            log.error("create {}{} file fail ", configUtils.getUploadFilePath(), fileName);
            return false;
        }
        return true;
    }
}