package com.demo.date;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

/**
 * @author lin.wang
 * @date 2020/06/16
 * Java 8开始，明确了日期时间概念，例如：瞬时（instant）、 长短（duration）、日期、时间、时区和周期。
 *
 * 同时继承了Joda 库按人类语言和计算机各自解析的时间处理方式。不同于老版本，新API基于ISO标准日历系统，java.time包下的所有类都是不可变类型而且线程安全。
 */
public class TestDate {

    public static void main(String[] args) throws Exception{
        System.out.println(LocalDateTime.now());
        String DateNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateNow);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));

        System.out.println(sdf.parse("2017-08-18 19:03:25"));
    }
}