package com.demo.swagger;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author lin.wang
 * @date 2020/07/02
 */
@Setter
@Getter
@AllArgsConstructor
@Data
public class User {

    @NotNull(message = "更新时id不能为空")
    @ApiModelProperty(value = "ID", example = "用户id")
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    private String name;

    @Max(value = 30, message = "年龄不能超过30岁")
    private Integer age;

}
