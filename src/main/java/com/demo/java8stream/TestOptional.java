package com.demo.java8stream;

import com.google.common.collect.Lists;
import lombok.*;

import java.util.List;
import java.util.Optional;

/**
 * @author lin.wang
 * @date 2020/06/24
 */
public class TestOptional {

    public static void main(String[] args) {
        System.out.println("==============================");
        User u = User.builder().name("xiaoming").age(20).build();

        // System.out.println(Optional.of(null).isPresent());

        System.out.println(Optional.ofNullable(u).map(user -> user.getName()).orElse("--"));

        System.out.println(Optional.ofNullable(u).orElse(null));

        System.out.println(Optional.ofNullable(null).orElse(User.builder().name("---").build()));


        System.out.println("==============================");
        Dept dept = Dept.builder().deptName("dept").deptNo(1).build();
        User u1 = User.builder().name("xiaoming").age(20).dept(dept).build();

        System.out.println(Optional.ofNullable(u1)
                .map(user -> user.getDept())
                .map(dept1 -> dept1.getDeptName())
                .orElse("--"));

        System.out.println(Optional.ofNullable(u1)
                .map(user -> user.getDept())
                .map(dept1 -> dept1.getDeptName())
                .isPresent());

        System.out.println("==============================");
        User u2 = User.builder().name("xiaoming").age(20).build();

        System.out.println(Optional.ofNullable(u2)
                .map(user -> user.getDept())
                .map(dept1 -> dept1.getDeptName())
                .orElse("--"));

        System.out.println(Optional.ofNullable(u2)
                .map(user -> user.getDept())
                .map(dept1 -> dept1.getDeptName())
                .isPresent());


        System.out.println("==============================");
        List<User> userList = Lists.newArrayList();
        userList.add(User.builder().age(1).name("11").build());
        userList.add(User.builder().age(2).name("22").build());
        userList.add(User.builder().age(3).name("33").build());
        System.out.println(userList);
        Optional<User> userOptional = userList.stream().filter(user ->
                String.valueOf(user.getAge()).equals(String.valueOf(2))).findFirst();
        User user = userOptional.get();
        System.out.println(user);
        // userList.set(userList.indexOf(user), User.builder().age(4).name("44").build());
        userList.remove(user);
        System.out.println(userList);

        System.out.println("==============================");
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class User {
    private String name;
    private Integer age;
    private Dept dept;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Dept {
    private String deptName;
    private Integer deptNo;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Role {
    private String name;
    private Integer age;
    private List<User> userList;
}