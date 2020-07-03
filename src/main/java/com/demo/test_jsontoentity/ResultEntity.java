package com.demo.test_jsontoentity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhoushimiao
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ResultEntity implements Serializable {
    private boolean status;
    private Integer code;
    private String msg;

    public static ResultEntity getFailResultEntity(int code, String msg) {
        return new ResultEntity(false, code, msg);
    }
}