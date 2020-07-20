package com.demo.date;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

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


        System.out.println("************list 降序排列************************");

        ArrayList<TestUser> list1 = Lists.newArrayList();
        TestUser t1 = new TestUser();
        t1.setDate("2020-07-07 11:10:10");
        list1.add(t1);
        t1 = new TestUser();
        t1.setDate("2020-07-07 12:10:10");
        list1.add(t1);
        ArrayList<TestUser> list2 = Lists.newArrayList();
        t1 = new TestUser();
        t1.setDate("2020-07-07 13:10:10");
        list2.add(t1);
        t1 = new TestUser();
        t1.setDate("2020-07-07 09:10:10");
        list2.add(t1);
        ArrayList<TestUser> list3 = Lists.newArrayList();
        list3.addAll(list1);
        list3.addAll(list2);

        System.out.println("排序前");
        list3.forEach(u ->{
            System.out.println(u.getDate());
        });

        Collections.sort(list3, Comparator.comparing(TestUser::getDate, (t3, t2) -> t2.compareTo(t3)));
        System.out.println("排序后");
        list3.subList(0, 2).forEach(u ->{
            System.out.println(u.getDate());
        });

        System.out.println("-----");
        list3.subList(0, (list3.size() > 50) ? 50: list3.size()).forEach(u ->{
            System.out.println(u.getDate());
        });


        System.out.println("*********************************");
        testJsonFormat();
    }

    private static void testJsonFormat() throws Exception{
        TestUser t1 = new TestUser();
        t1.setCreatedTime(new Date());
        t1.setUpdatedTime(new Date());
        System.out.println(JSON.toJSONString(t1));
        System.out.println(t1);
    }

}
