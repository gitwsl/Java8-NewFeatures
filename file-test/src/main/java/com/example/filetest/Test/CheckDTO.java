package com.example.filetest.Test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liaobaohuang
 * @date 2020/9/2
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckDTO {
    private Integer type;

    private String accountId;

    private String currentNodeId;

    private String modelDefinitionId;

    private String userId;

    private String workOrderId;

    private List<CheckFileDTO> fileList;

}