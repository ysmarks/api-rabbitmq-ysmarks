package com.example.apirabbitmqysmarks.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.apirabbitmqysmarks.domain.Notification;
import com.google.gson.Gson;

@Component
public class RabbitMqListener {
	
	@RabbitListener(queues = "${rabbitmq.queueName}")
	public void listen(byte[] message) {
		
		String msg = new String(message);
		Notification notification = new Gson().fromJson(msg, Notification.class);
		System.out.println("Recebendo a notificação");
		System.out.println(notification.toString());
		
	}

}
