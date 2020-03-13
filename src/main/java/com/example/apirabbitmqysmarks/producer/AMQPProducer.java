package com.example.apirabbitmqysmarks.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.apirabbitmqysmarks.config.RabbitMQProperties;
import com.example.apirabbitmqysmarks.domain.Notification;

@Component
public class AMQPProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private RabbitMQProperties rabbitMQProperties;
	
	public void sendMessage(Notification notification) {
		System.out.println("Enviando mensagem = " + notification.toString());
		rabbitTemplate.convertAndSend(rabbitMQProperties.getExchangeName(), rabbitMQProperties.getRoutingKey(), notification);
	}
}
