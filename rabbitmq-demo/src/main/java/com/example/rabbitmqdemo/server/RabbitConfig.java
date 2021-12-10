package com.example.rabbitmqdemo.server;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author liuxin1
 */
@Configuration
@EnableRabbit
public class RabbitConfig {

	@Autowired
	private ConnectionFactory connectionFactory;

	/**
	 * rabbitAdmin代理类
	 */
	@Bean
	public RabbitAdmin rabbitAdmin() {
		RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
		rabbitAdmin.setAutoStartup(true);
		return rabbitAdmin;
	}

	/**
	 * 创建rabbitTemplate 消息模板类，prototype原型模式:每次获取Bean的时候会有一个新的实例
	 */
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	//必须是prototype类型
 	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMandatory(true);
		return rabbitTemplate;
	}

}
