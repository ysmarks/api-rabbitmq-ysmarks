package com.example.apirabbitmqysmarks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apirabbitmqysmarks.componentes.OrderQueueSender;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired
	private OrderQueueSender orderQueueSender;
	
	@PostMapping
	public void send(@RequestBody String order) {
		log.info(order);
		orderQueueSender.send(order);
	}
}
