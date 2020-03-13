package com.example.apirabbitmqysmarks.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;


@Configuration
public class AMQPConfig {
	
	@Autowired
	private RabbitMQProperties rabbitMQProperties;
	
	@Bean
	Queue queue() {
		return new Queue(rabbitMQProperties.getQueueName(), false);
	}
	
	@Bean
	TopicExchange exchangeTopic() {
		return new TopicExchange(rabbitMQProperties.getExchangeName());
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(rabbitMQProperties.getExchangeName());
	}
	
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(rabbitMQProperties.getRoutingKey());
	}
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer c = new SimpleMessageListenerContainer();
		c.setConnectionFactory(connectionFactory);
		c.setQueueNames(rabbitMQProperties.getQueueName());
		c.setMessageListener(listenerAdapter);
		
		return c;
	}
	
	@Bean
	public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
		return new MappingJackson2MessageConverter();
	}
	
	@Bean
	public RabbitTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter());
		
		return rabbitTemplate;
		
	}

	@Bean
	public Jackson2JsonMessageConverter messageConverter() {
		
		return new Jackson2JsonMessageConverter();
	}
	
}
