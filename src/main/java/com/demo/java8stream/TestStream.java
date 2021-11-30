package com.demo.java8stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author lin.wang
 * @date 2020/06/15
 */
public class TestStream {
    public static void main(String[] args) {
        test1();
    }


    public static List<Emp> list = new ArrayList<>();

    static {
        list.add(new Emp("上海", "小名", 17));
        list.add(new Emp("北京", "小红", 18));
        list.add(new Emp("深圳", "小蓝", 19));
        list.add(new Emp("广州", "小灰", 20));
        list.add(new Emp("杭州", "小黄", 21));
        list.add(new Emp("贵阳", "小白", 22));
        list.add(new Emp("河南", "小王", 24));
    }

    public static void test1() {
        // 转list
        List<String> names = list.stream().map(emp -> emp.getName()).collect(Collectors.toList());
        System.out.println(names);
        // 转set
        Set<String> address = list.stream().map(emp -> emp.getName()).collect(Collectors.toSet());
        System.out.println(address);
        // 转map，需要指定key和value，Function.identity()表示当前的Emp对象本身
        Map<String, Emp> map = list.stream().collect(Collectors.toMap(Emp::getName, Function.identity()));
        System.out.println(map);
        // 计算元素中的个数
        Long count = list.stream().collect(Collectors.counting());
        // 数据求和 summingInt summingLong，summingDouble
        Integer sumAges = list.stream().collect(Collectors.summingInt(Emp::getAge));
        // 平均值 averagingInt,averagingDouble,averagingLong
        Double aveAges = list.stream().collect(Collectors.averagingInt(Emp::getAge));

        // 综合处理的，求最大值，最小值，平均值，求和操作
        // summarizingInt，summarizingLong,summarizingDouble
        IntSummaryStatistics intSummary = list.stream().collect(Collectors.summarizingInt(Emp::getAge));
        System.out.println(intSummary.getAverage());// 19.5
        System.out.println(intSummary.getMax());// 22
        System.out.println(intSummary.getMin());// 17
        System.out.println(intSummary.getSum());// 117

        // 连接字符串,当然也可以使用重载的方法，加上一些前缀，后缀和中间分隔符
        String strEmp = list.stream().map(emp -> emp.getName()).collect(Collectors.joining());
        String strEmp1 = list.stream().map(emp -> emp.getName()).collect(Collectors.joining("-中间的分隔符-"));
        String strEmp2 = list.stream().map(emp -> emp.getName()).collect(Collectors.joining("-中间的分隔符-", "前缀*", "&后缀"));
        System.out.println(strEmp);// 小名小红小蓝小灰小黄小白
        // 小名-中间的分隔符-小红-中间的分隔符-小蓝-中间的分隔符-小灰-中间的分隔符-小黄-中间的分隔符-小白
        System.out.println(strEmp1);
        // 前缀*小名-中间的分隔符-小红-中间的分隔符-小蓝-中间的分隔符-小灰-中间的分隔符-小黄-中间的分隔符-小白&后缀
        System.out.println(strEmp2);
        // maxBy 按照比较器中的比较结果刷选 最大值
        Optional<Integer> maxAge = list.stream().map(emp -> emp.getAge())
                .collect(Collectors.maxBy(Comparator.comparing(Function.identity())));
        // 最小值
        Optional<Integer> minAge = list.stream().map(emp -> emp.getAge())
                .collect(Collectors.minBy(Comparator.comparing(Function.identity())));
        System.out.println("max:" + maxAge);
        System.out.println("min:" + minAge);

        // 归约操作
        list.stream().map(emp -> emp.getAge()).collect(Collectors.reducing((x, y) -> x + y));
        list.stream().map(emp -> emp.getAge()).collect(Collectors.reducing(0, (x, y) -> x + y));
        // 分操作 groupingBy 根据地址，把原list进行分组
        Map<String, List<Emp>> mapGroup = list.stream().collect(Collectors.groupingBy(Emp::getAddress));
        // partitioningBy 分区操作 需要根据类型指定判断分区
        Map<Boolean, List<Integer>> partitioningMap = list.stream().map(emp -> emp.getAge())
                .collect(Collectors.partitioningBy(emp -> emp > 20));

    }

    static class Emp {
        private String address;

        private String name;

        private Integer age;

        public Emp() {

        }

        public Emp(String address) {
            this.address = address;
        }

        public Emp(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public Emp(String address, String name, Integer age) {
            super();
            this.address = address;
            this.name = name;
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Emp [address=" + address + ", name=" + name + ", age=" + age + "]";
        }

    }
}