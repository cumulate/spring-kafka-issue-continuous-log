package com.tmt.springkafkaissue;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.header.Headers;

import java.util.Map;

@Slf4j
public class KafkaConsumerInterceptor implements ConsumerInterceptor<String, Object> {

    private KafkaConsumerDecorator kafkaConsumerDecorator;

    @Override
    public void configure(Map<String, ?> configs) {
        if (configs.get("consumer.decorator") != null) {
            this.kafkaConsumerDecorator = (KafkaConsumerDecorator) configs.get("consumer.decorator");
        }
    }

    @Override
    public ConsumerRecords<String, Object> onConsume(ConsumerRecords<String, Object> records) {
        // Should use header as it will override by last thread.
        log.info("Intercepting Kafka Consumer Message Request");
        records.forEach(this::readUserInfoHeader);
        if (kafkaConsumerDecorator != null) {
            records.forEach(kafkaConsumerDecorator::decorate);
        }
        return records;
    }

    private void readUserInfoHeader(ConsumerRecord<String, Object> record) {
        log.info("Initializing UserInfo and TraceId on Kafka Consumer Message");
        Headers headers = record.headers();



    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {
    }

    @Override
    public void close() {
    }

}
