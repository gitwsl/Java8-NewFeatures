package com.example.pagehelperdemo.dao;

import com.example.pagehelperdemo.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lin.wang
 * @date 2020/07/03
 */
// 加上@Repository注解，service不飘红（红不影响使用，只是不美观，所以这里加上注解）
// 真正有用的注解，@Mapper，这里咱们在启动类上加了@MapperScan，相当于所有dao层都加了，意思是
// dao层实现类交给spring管理，可实现依赖注入
@Repository
public interface UserDao {

    @Select("select * from user_info")
    List<UserInfoEntity> findAll();
}
