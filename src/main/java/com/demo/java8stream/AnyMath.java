package com.demo.java8stream;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lin.wang
 * @date 2020/07/16
 */
public class AnyMath {

    public static List<Emp> empList = new ArrayList<>();

    {
        empList.add(new Emp("上海", "小名", 17));
        empList.add(new Emp("北京", "小红", 18));
        empList.add(new Emp("深圳", "小蓝", 19));
        empList.add(new Emp("广州", "小灰", 20));
        empList.add(new Emp("杭州", "小黄", 21));
        empList.add(new Emp("贵阳", "小白", 22));
    }

    public static void main(String[] args) {
        List<User> userList = Lists.newArrayList();
        Dept dept = Dept.builder().deptName("dept1").deptNo(1).build();
        userList.add(User.builder().name("11").age(1).dept(dept).build());

        dept = Dept.builder().deptName("dept2").deptNo(2).build();
        userList.add(User.builder().age(2).name("22").dept(dept).build());

        dept = Dept.builder().deptName("dept3").deptNo(3).build();
        userList.add(User.builder().age(3).name("33").dept(dept).build());


        List<User> collect = userList.stream().filter(item -> item.getAge().compareTo(1) == 0).collect(Collectors.toList());

        System.out.println(collect);
        //        Stream<String> stream = Stream.of("a","b");
//        Stream<String> stream1 = stream.filter((String name)->{
//            return  name.startsWith("a");
//        });
//        stream1.forEach((String name)->{System.out.println(name);});
    }

    class Emp {
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
