package com.demo.hutool;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;

import java.io.File;
import java.util.Base64;

/**
 * @author lin.wang
 * @date 2020/09/17
 * 生成带logo的二维码
 */
public class QrConfig1 {
    public static void main(String[] args) {
        String a = Base64.getEncoder().encodeToString(QrCodeUtil.generatePng("https://www.guduke.cn/", 450, 450));
        System.out.println(a);
        QrConfig qrConfig = new QrConfig();
        qrConfig.setHeight(450);
        qrConfig.setWidth(450);
        qrConfig.setImg("/Users/edz/Downloads/流程图.png");
        QrCodeUtil.generate("http://www.baidu.com/",qrConfig,new File("/Users/edz/Downloads/流程图1.png"));
    }
}
