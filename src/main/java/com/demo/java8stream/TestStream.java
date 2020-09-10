package com.demo.java8stream;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author lin.wang
 * @date 2020/06/15
 */
@Slf4j
public class TestStream {
    public static void main(String[] args) {
//        test1();
        test2();
    }


    public static List<Emp> list = new ArrayList<>();

    static {
        list.add(new Emp("上海", "小名", 17));
        list.add(new Emp("北京", "小红", 18));
        list.add(new Emp("深圳", "小蓝", 19));
        list.add(new Emp("广州", "小灰", 20));
        list.add(new Emp("杭州", "小黄", 21));
        list.add(new Emp("贵阳", "小白", 22));
    }

    public static void test2() {
        List<UmsPermission> permissionList = new ArrayList<>();
        permissionList.add(new UmsPermission(1L, "11",3));
        permissionList.add(new UmsPermission(2L, "22",2));
        permissionList.add(new UmsPermission(3L, "33",1));
        permissionList.add(new UmsPermission(4L, "44",1));
        //filter操作：获取权限类型为目录的权限
        List<UmsPermission> dirList = permissionList.stream()
                .filter(permission -> permission.getType() == 1)
                .collect(Collectors.toList());
        log.info("filter操作：{}" + dirList);
        //map操作：获取所有权限的id
        List<Long> idList = permissionList.stream()
                .map(permission -> permission.getId())
                .collect(Collectors.toList());
        log.info("map操作：{}", idList);
        //limit操作：获取前5个权限
        List<UmsPermission> firstFiveList = permissionList.stream()
                .limit(5)
                .collect(Collectors.toList());
        log.info("limit操作：{}", firstFiveList);
        //count操作：获取所有权限目录权限的个数
        long dirPermissionCount = permissionList.stream()
                .filter(permission -> permission.getType() == 1)
                .count();
        log.info("count操作：{}", dirPermissionCount);
        //sorted操作：将所有权限按先目录后菜单再按钮的顺序排序
        List<UmsPermission> sortedList = permissionList.stream()
                .sorted((permission1, permission2) -> {
                    return permission1.getType().compareTo(permission2.getType());
                })
                .collect(Collectors.toList());
        log.info("sorted操作：{}", sortedList);
        //skip操作：跳过前5个元素，返回后面的
        List<UmsPermission> skipList = permissionList.stream()
                .skip(5)
                .collect(Collectors.toList());
        log.info("skip操作：{}", skipList);
        //collect转map操作：将权限列表以id为key，以权限对象为值转换成map
        Map<Long, UmsPermission> permissionMap = permissionList.stream()
                .collect(Collectors.toMap(permission -> permission.getId(), permission -> permission));
        log.info("collect转map操作：{}", permissionMap);
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


    static class UmsPermission {
        private Long id;

//        @ApiModelProperty(value = "名称")
        private String name;


//        @ApiModelProperty(value = "权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）")
        private Integer type;

        public UmsPermission(){}
        public UmsPermission(Long id, String name, Integer type){
            this.id = id;
            this.name = name;
            this.type = type;
        }

        private static final long serialVersionUID = 1L;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "UmsPermission{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", type=" + type +
                    '}';
        }
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