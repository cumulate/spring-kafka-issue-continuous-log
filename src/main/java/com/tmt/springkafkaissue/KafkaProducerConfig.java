package com.tmt.springkafkaissue;



import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
@EnableKafka
public class KafkaProducerConfig {

    private final KafkaConfig kafkaConfiguration;

    private final MappingJackson2HttpMessageConverter springJacksonConverter;

    public KafkaProducerConfig(@Qualifier("defaultKafkaConfiguration") KafkaConfig kafkaConfiguration,
                               MappingJackson2HttpMessageConverter springJacksonConverter) {
        this.kafkaConfiguration = kafkaConfiguration;
        this.springJacksonConverter = springJacksonConverter;
    }

    @Bean
    KafkaTemplate<String, BaseKafkaEvent> defaultKafkaTemplate() {
        return new KafkaTemplate<>(defaultProducerFactory());
    }

    @Bean
    public ProducerFactory<String, BaseKafkaEvent> defaultProducerFactory() {
        final DefaultKafkaProducerFactory<String, BaseKafkaEvent> producerFactory = new DefaultKafkaProducerFactory<>(
                kafkaConfiguration.producerConfigs());
        producerFactory.setValueSerializer(getJsonSerializer());
        return producerFactory;
    }



    @Bean
    public KafkaTemplate<String, MailReceivedStatusUpdateEvent> mailReceivedStatusUpdateEventKafkaTemplate() {
        return new KafkaTemplate<>(mailReceivedStatusUpdateEventProducerFactory());
    }

    @Bean
    public ProducerFactory<String, MailReceivedStatusUpdateEvent> mailReceivedStatusUpdateEventProducerFactory() {
        final DefaultKafkaProducerFactory<String, MailReceivedStatusUpdateEvent> producerFactory = new
                DefaultKafkaProducerFactory<>(kafkaConfiguration.producerConfigs());
        producerFactory.setValueSerializer(getJsonSerializer());
        return producerFactory;
    }


    private <T> JsonSerializer<T> getJsonSerializer() {
        return new JsonSerializer<>(springJacksonConverter.getObjectMapper());
    }
}

