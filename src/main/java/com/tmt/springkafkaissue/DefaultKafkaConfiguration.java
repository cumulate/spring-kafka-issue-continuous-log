package com.tmt.springkafkaissue;


import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Slf4j
@Configuration("defaultKafkaConfiguration")
public class DefaultKafkaConfiguration extends BaseKafkaConfig {

    private final ConfigProperties configProperties;

    public DefaultKafkaConfiguration(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    @Override
    public Map<String, Object> producerConfigs() {
        Map<String, Object> producerConfigs = super.producerConfigs();
        producerConfigs.put(SaslConfigs.SASL_JAAS_CONFIG, configProperties.getSaslJaasConfig());
        producerConfigs.put(SaslConfigs.SASL_MECHANISM, configProperties.getSaslMechanism());
        producerConfigs.put(CommonClientConfigs.CLIENT_DNS_LOOKUP_CONFIG, configProperties.getClientDnsLookup());
        producerConfigs.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, configProperties.getSecurityProtocol());
        producerConfigs.put(CommonClientConfigs.SESSION_TIMEOUT_MS_CONFIG, configProperties.getSessionTimeoutMs());
        producerConfigs.put(ProducerConfig.ACKS_CONFIG, configProperties.getAcks());

        addCommonConfig(producerConfigs);

        return producerConfigs;
    }

    @Override
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> configs = super.consumerConfigs();
        configs.put(SaslConfigs.SASL_JAAS_CONFIG, configProperties.getSaslJaasConfig());
        configs.put(SaslConfigs.SASL_MECHANISM, configProperties.getSaslMechanism());
        configs.put(CommonClientConfigs.CLIENT_DNS_LOOKUP_CONFIG, configProperties.getClientDnsLookup());
        configs.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, configProperties.getSecurityProtocol());
        configs.put(CommonClientConfigs.SESSION_TIMEOUT_MS_CONFIG, configProperties.getSessionTimeoutMs());

        addCommonConfig(configs);

        return configs;
    }


    private void addCommonConfig(Map<String, Object> configs) {
       /* if (StringUtils.isNotBlank(configProperties.getClientCallbackHandlerClass())) {
            configs.put(SaslConfigs.SASL_CLIENT_CALLBACK_HANDLER_CLASS, configProperties.getClientCallbackHandlerClass());
            configs.put(SaslConfigs.SASL_MECHANISM, configProperties.getSaslMechanism());
        }*/
    }

}
