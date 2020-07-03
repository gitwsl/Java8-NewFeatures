package com.demo.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

/**
 * @author lin.wang
 * @date 2020/07/01
 */
@Setter
@Getter
@AllArgsConstructor
@Data
public class User {
    public interface Update {}
    public interface Insert {}
    public interface Delete {}


    @NotNull(groups = {Update.class, Delete.class}, message = "更新时id不能为空")
    private Integer id;

    @NotBlank(groups = {Insert.class}, message = "用户名不能为空")
    private String name;

    @Max(value = 30, message = "年龄不能超过30岁")
    private Integer age;

}
