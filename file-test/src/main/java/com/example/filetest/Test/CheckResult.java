package com.example.filetest.Test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lin.wang
 * @date 2020/09/07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckResult {
    private String code;
    private String message;
    private CheckResultExecCode result;
}
