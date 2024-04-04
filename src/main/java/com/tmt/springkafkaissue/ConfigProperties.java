package com.tmt.springkafkaissue;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ConfigProperties {

    @Value("${kafka.consumer.group-id}")
    private String consumerGroupId;

    @Value("${kafka.client-id}")
    private String clientId;

    @Value("${spring.kafka.properties.sasl.jaas.config}")
    private String saslJaasConfig;

    @Value("${spring.kafka.properties.sasl.mechanism}")
    private String saslMechanism;

    @Value("${spring.kafka.client.dns.lookup}")
    private String clientDnsLookup;

    @Value("${spring.kafka.properties.security.protocol}")
    private String securityProtocol;

/*    @Value("${spring.kafka.properties.sasl.client.callback.handler.class}")
    private String clientCallbackHandlerClass;*/

    @Value("${spring.kafka.session.timeout.ms}")
    private String sessionTimeoutMs;

    @Value("${spring.kafka.producer.acks}")
    private String acks;

    @Value("${kafka.custom.topic.consumers.mail-received-event-topic}")
    private String mailReceivedEventTopic;


}
