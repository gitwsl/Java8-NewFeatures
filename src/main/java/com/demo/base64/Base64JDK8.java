package com.demo.base64;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

/**
 * 与sun.misc套件和Apache Commons Codec所提供的Base64编解码器来比较的话，
 * Java 8提供的Base64拥有更好的效能。
 * 实际测试编码与解码速度的话，Java 8提供的Base64，要比sun.misc套件提供的还要快至少11倍，
 * 比Apache Commons Codec提供的还要快至少3倍。
 * 因此在Java上若要使用Base64，这个Java 8底下的java .util套件所提供的Base64类别绝对是首选！
 * 参考链接：https://magiclen.org/java-base64/
 */
public class Base64JDK8 {

    private static final String DATA = "加解密测试";

    public static void main( String[] args ) {
        try {
            // BASE64加密
            Encoder encoder = Base64.getEncoder();
            byte[] data = encoder.encode(DATA.getBytes());
            System.out.println("BASE64加密：" + new String(data));
            // 或者采用以下方法，但是不赞成使用该方法，源码也做了@deprecation标记
            System.out.println("BASE64加密：" + encoder.encodeToString(DATA.getBytes()));
//    		-----------------------------------------------------------
//            @SuppressWarnings("deprecation")
//            public String encodeToString(byte[] src) {
//                byte[] encoded = encode(src);
//                return new String(encoded, 0, 0, encoded.length);
//            }
//    		-----------------------------------------------------------

            // BASE64解密
            Decoder decoder = Base64.getDecoder();
            byte[] bytes = decoder.decode(data);
            System.out.println("BASE64解密：" + new String(bytes));

            // 结果
            // BASE64加密：Y29tLmJhc2U2NC5kZW1v
            // BASE64解密：com.base64.demo
        } catch (Exception e) {
            System.out.println("BASE64加解密异常");
            e.printStackTrace();
        }
    }
}

