package com.example.filetest.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lin.wang
 * @date 2020/09/04
 */
@RestController
public class FileUploadController {

    @RequestMapping(value = {"upload","api/v2/verify/0/excel", "api/v2/verify/1/excel"}, method = RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File("-uploaded.xlsx")));
                stream.write(bytes);
                stream.close();

                Map<String, Object> map = new HashMap();
                map.put("code", 200);
                map.put("message", "校验成功");
                Map<String, String> result = new HashMap();
                result.put("execCode", "5X6AZSWEDSA8QWESADSW");
                map.put("result", result);
                return JSON.toJSONString(map);
//                return "You successfully uploaded into -uploaded !";
            } catch (Exception e) {
                return "You failed to upload  => " + e.getMessage();
            }
        } else {
            return "You failed to upload  because the file was empty.";
        }
    }

    @RequestMapping(value="/batch/upload", method= RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        for (int i =0; i< files.size(); ++i) {
            MultipartFile file = files.get(i);
            String name = file.getName();
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    BufferedOutputStream stream =
                            new BufferedOutputStream(new FileOutputStream(new File(name + i)));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    return "You failed to upload " + name + " => " + e.getMessage();
                }
            } else {
                return "You failed to upload " + name + " because the file was empty.";
            }
        }
        return "upload successful";
    }
}
