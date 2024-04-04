package com.tmt.springkafkaissue;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaConsumerDecorator {

    void decorate(ConsumerRecord<String, Object> record);

}
