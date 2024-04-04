package com.tmt.springkafkaissue;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Slf4j
@EnableKafka
@Configuration
public class KafkaConsumerConfig extends BaseKafkaConfig {

    private final MappingJackson2HttpMessageConverter springJacksonConverter;

    private final KafkaConfig kafkaConfig;

    public KafkaConsumerConfig(@Qualifier("mappingJackson2HttpMessageConverter") final MappingJackson2HttpMessageConverter springJacksonConverter,
                               @Qualifier("defaultKafkaConfiguration") KafkaConfig kafkaConfig) {
        this.springJacksonConverter = springJacksonConverter;
        this.kafkaConfig = kafkaConfig;
    }

    @Bean
    public ConsumerFactory<String, MailReceivedEvent> mailReceivedEventConsumerFactory() {
        final DefaultKafkaConsumerFactory<String, MailReceivedEvent> consumerFactory = new DefaultKafkaConsumerFactory<>(kafkaConfig.consumerConfigs());
        consumerFactory.setValueDeserializer(getJsonDeserializer());
        return consumerFactory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MailReceivedEvent> mailReceivedEventListenerContainerFactory() {
        final ConcurrentKafkaListenerContainerFactory<String, MailReceivedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(mailReceivedEventConsumerFactory());
        return factory;
    }



    private <T> JsonDeserializer<T> getJsonDeserializer() {
        JsonDeserializer<T> jsonDeserializer = new JsonDeserializer<>(springJacksonConverter.getObjectMapper());
        jsonDeserializer.addTrustedPackages("*");
        return jsonDeserializer;
    }
}
