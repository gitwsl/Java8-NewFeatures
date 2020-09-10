package com.demo.httpClient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lin.wang
 * @date 2020/06/18
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpClientResult implements Serializable {

    /**
     * http 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private String content;

    public HttpClientResult(int code) {
        this.code = code;
    }
}
