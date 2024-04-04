package com.tmt.springkafkaissue;

import org.apache.kafka.clients.producer.ProducerRecord;

public interface KafkaProducerDecorator {

    void decorate(ProducerRecord<String, Object> record);

}
