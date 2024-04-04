package com.tmt.springkafkaissue;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.header.Headers;

import java.util.Map;


@Slf4j
public class KafkaProducerInterceptor implements ProducerInterceptor<String, Object> {
    private KafkaProducerDecorator kafkaProducerDecorator;

    @Override
    public void configure(Map<String, ?> configs) {
        log.info("KafkaProducerInterceptor configure is invoked");
        if (configs.get("producer.decorator") != null) {
            this.kafkaProducerDecorator = (KafkaProducerDecorator) configs.get("producer.decorator");
        }
    }

    @Override
    public ProducerRecord<String, Object> onSend(ProducerRecord<String, Object> record) {
        Headers headers = record.headers();
        log.info("Intercepting Kafka Producer Message Request");


        log.info("onSend is completed");
        return record;
    }


    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        log.info("onAcknowledgement is invoked");
    }

    @Override
    public void close() {
        log.info("close is invoked");
    }



}
