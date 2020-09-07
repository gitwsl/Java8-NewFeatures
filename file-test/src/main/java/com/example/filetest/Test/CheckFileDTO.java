package com.example.filetest.Test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lin.wang
 * @date 2020/09/04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckFileDTO {
    private String alias;

    private String name;

    private String status;

    private String uid;

    private String url;
}
