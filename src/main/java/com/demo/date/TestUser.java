package com.demo.date;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lin.wang
 * @date 2020/07/13
 */
@Setter
@Getter
@Data
public class TestUser implements Serializable {
    private String date;

    @JSONField(format = "yyyy-MM-dd")
    private Date createdTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
}
