package com.example.pagehelperdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 学生信息实体类
 * @Author Sans
 * @CreateTime 2019/5/26 21:41
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoEntity {

    /**
     * 主键
     * TableId中可以决定主键的类型,不写会采取默认值,默认值可以在yml中配置
     * AUTO: 数据库ID自增
     * INPUT: 用户输入ID
     * ID_WORKER: 全局唯一ID，Long类型的主键
     * ID_WORKER_STR: 字符串全局唯一ID
     * UUID: 全局唯一ID，UUID类型的主键
     * NONE: 该类型为未设置主键类型
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 技能
     */
    private String skill;
    /**
     * 评价
     */
    private String evaluate;
    /**
     * 分数
     */
    private Long fraction;

    public static void main(String[] args) {
        Integer i = null;
        System.out.println(new Integer(i).intValue());
    }
}
