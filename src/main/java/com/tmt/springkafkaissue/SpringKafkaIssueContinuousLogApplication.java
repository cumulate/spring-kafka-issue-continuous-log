package com.tmt.springkafkaissue;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class SpringKafkaIssueContinuousLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaIssueContinuousLogApplication.class, args);
	}


	@Bean
	public NewTopic topic() {
		return TopicBuilder.name("PollingIssueTopic")
				.partitions(3)
				.replicas(1)
				.build();
	}

	@KafkaListener(id = "myId", topics = "PollingIssueTopic")
	public void listen(String in) {
		System.out.println(in);
	}
}
