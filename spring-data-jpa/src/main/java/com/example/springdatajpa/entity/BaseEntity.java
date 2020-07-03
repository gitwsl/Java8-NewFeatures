package com.example.springdatajpa.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author lin.wang
 * @date 2020/07/03
 */

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name= "create_date")
    private Date createDate = new Date();

    @Column(name= "update_date")
    private Date updateDate;
}
