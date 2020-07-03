package com.demo.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
/**
 * @author lin.wang
 * @date 2020/06/27
 *
 * TODO 不能执行，只是用来学习源码
 */
public class Mybatis {

        public SqlSessionFactory getSqlSessionFactory() throws IOException {
            // mybatis配置文件，这个地方的root地址为：resources，路径要对。
            String resource = "mybatis-config.xml";
            // 得到配置文件流
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // 创建会话工厂，传入mybatis的配置文件信息
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            return sqlSessionFactory;
        }

        // 根据id查询用户信息，得到一条记录结果
        public void findUserByIdTest() throws IOException {

            // 通过工厂得到SqlSession
            SqlSession sqlSession = this.getSqlSessionFactory().openSession();

            // 通过SqlSession操作数据库
            // 第一个参数：映射文件中statement的id，等于=namespace+"."+statement的id
            // 第二个参数：指定和映射文件中所匹配的parameterType类型的参数
            // sqlSession.selectOne结果 是与映射文件中所匹配的resultType类型的对象

            // selectOne查询出一条记录（这种很麻烦的！！！往后看看）
            //这里的参数test.findUserById，test为命名空间，要与user.xml中的对应起来，
            //同理，findUserById也要在user.xml中存在，不然都会报错
            User user = sqlSession.selectOne("test.findUserById", 1);
            System.out.println(user.getId());
            System.out.println(user.getName());
            System.out.println(user.getAge());
            // 释放资源
            sqlSession.close();
        }
}
