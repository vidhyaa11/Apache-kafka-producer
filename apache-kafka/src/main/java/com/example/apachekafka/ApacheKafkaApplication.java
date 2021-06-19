package com.example.apachekafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApacheKafkaApplication {
	
	
	@Autowired
	private KafkaTemplate<String, Object> temp;
	
	private String topic = "usertopic";
	
	@GetMapping(value="/publish/{name}")
	public String publishEvent(@PathVariable String name) {
		temp.send(topic, "Hi "+ name + " Welcome There!");
		return "Data Published";
	}
	
	@GetMapping(value="/publishJson")
	public String publishRawEvent() {
		User user1 = new User(123, "vidhyaa", new String[] {"Madipakkam", "Chennai"});
		temp.send(topic, user1);
		return "JSON Data Published";
	}


	public static void main(String[] args) {
		SpringApplication.run(ApacheKafkaApplication.class, args);
	}

}
