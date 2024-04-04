package com.tmt.springkafkaissue;

import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

public interface KafkaConfig {
    Map<String, Object> producerConfigs();

    Map<String, Object> consumerConfigs();

    ProducerFactory<String, Object> producerFactory();

    ConsumerFactory<String, Object> consumerFactory();
}
