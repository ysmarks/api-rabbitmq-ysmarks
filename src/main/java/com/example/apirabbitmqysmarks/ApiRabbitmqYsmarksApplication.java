package com.example.apirabbitmqysmarks;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
/**
 * https://www.devglan.com/spring-boot/springboot-rabbitmq-example
 * @author ysmarks.santos
 *
 */
@SpringBootApplication
public class ApiRabbitmqYsmarksApplication {
	
	@Value("${destinations.queues}")
	private String orderQueue;

	public static void main(String[] args) {
		
		SpringApplication.run(ApiRabbitmqYsmarksApplication.class, args);
		
	}
	
	@Bean
	public ApplicationRunner runner(RabbitTemplate rabbitTemplate) {
		return args -> { rabbitTemplate.convertAndSend(orderQueue, "Teste Ysmarks");};
	}

	@Bean
	public Queue queue() {
		return new Queue(orderQueue, true);
	}
	
	@RabbitListener(queues = {"${destinations.queues}"})
	public void listen(String order) {
		System.out.println("mensagem escrita na orderQueue: " + order);
	}
}
