package com.demo.test_jsontoentity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.List;

/**
 * @author lin.wang
 * @date 2020/07/01
 * @JsonProperty 这个注解提供了序列化和反序列化过程中该java属性所对应的名称
 * @JsonAlias 这个注解只只在反序列化时起作用，指定该java属性可以接受的更多名称
 */
public class json_property {

    @Setter
    @Getter
    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    static class Label {
        @JsonProperty("NaMe")
        public String name;

        @JsonAlias("NaMe1")
        public String name1;

        @JsonProperty("role")
        private List<Role> resultDataList;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @JsonProperty("create_time")
        private Date createTime;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    static class Role {
        @JsonProperty("role")
        public String role1;
    }


    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
//        Label lable = new Label();
//        lable.setName("1");
//        lable.setName1("2");
//        Role role = new Role();
//        role.setRole("a");
//        List<Role> roleList = new ArrayList<>();
//        roleList.add(role);
//        role = new Role();
//        role.setRole("b");
//        roleList.add(role);
//        lable.setRole(roleList);
//        System.out.println(objectMapper.writeValueAsString(lable));

        String a = "{\"NaMe\":\"1\",\"name1\":\"2\",\"role\":[{\"role\":\"a\"},{\"role\":\"b\"}],\"create_time\":\"2020-01-01 01:01:01\"}";
        System.out.println("字符串： " + a);
        Label label = objectMapper.readValue(a, Label.class);
        System.out.println("----------反序列化------");
        System.out.println(label);
        System.out.println("----------序列化------");
        String labelString = objectMapper.writeValueAsString(label);
        System.out.println(labelString);


        System.out.println("######################");
        Test json = new Test();
        json.setCreateTime(new Date());
        System.out.println(objectMapper.writeValueAsString(json));
    }

    @Data
    static class Test {
        @JsonProperty("NaMe")
        public String name;

        @JsonAlias("NaMe1")
        public String name1;

        @JsonProperty("role")
        private List<Role> resultDataList;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @JsonProperty("create_time")
        private Date createTime;
    }
}
