package com.example.filetest.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Terry
 * @date : 2020/8/19  3:21 下午
 **/
@Data
@Configuration
public class ConfigUtils {

    @Value("${unicom.checkFile.check.hosts}")
    private String unicomHosts;

    @Value("${unicom.checkFile.save.hosts}")
    private String unicomSaveHosts;

    @Value("${web.upload.path}")
    private String uploadFilePath;
}