package com.tmt.springkafkaissue;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
@Profile({"defaultKafkaProfile"})
public class BaseKafkaConfig implements KafkaConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootStrapServer;
    @Value("${kafka.bootstrap-servers}")
    private String bootStrapServer2;
    @Value("${kafka.consumer.group-id}")
    private String consumerGroupId;
    @Value("${kafka.max.poll.records}")
    private Integer maxPollRecords;
    @Value("${kafka.auto.commit.interval.ms}")
    private Integer autoCommitInterval;
    @Value("${kafka.client-id}")
    private String clientId;

    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap();
        props.put("bootstrap.servers", this.getKafkaBootStrapServers());
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer", JsonSerializer.class);
        props.put("client.id", this.clientId);
     //   this.addInterceptor(props, "interceptor.classes", KafkaProducerInterceptor.class.getName());
        return props;
    }

    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap();
        props.put("bootstrap.servers", this.getKafkaBootStrapServers());
        props.put("key.deserializer", StringDeserializer.class);
        props.put("value.deserializer", JsonDeserializer.class);
        props.put("group.id", this.getConsumerGroupId());
        props.put("auto.offset.reset", "latest");
        props.put("max.poll.records", this.getMaxPollRecords());
        props.put("auto.commit.interval.ms", this.getAutoCommitInterval());
    //    this.addInterceptor(props, "interceptor.classes", KafkaConsumerInterceptor.class.getName());
        return props;
    }

    protected void addInterceptor(Map<String, Object> props, String interceptorClassesConfig, String interceptorClassName) {
        List<String> interceptors = (List)props.get(interceptorClassesConfig);
        if (interceptors == null) {
            interceptors = new ArrayList();
        }

        ((List)interceptors).add(interceptorClassName);
        props.put(interceptorClassesConfig, interceptors);
    }

    private String getKafkaBootStrapServers() {
        return this.bootStrapServer + "," + this.bootStrapServer2;
    }

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        return new DefaultKafkaProducerFactory(this.producerConfigs());
    }

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        return new DefaultKafkaConsumerFactory(this.consumerConfigs());
    }

    public BaseKafkaConfig() {
    }

    public String getBootStrapServer() {
        return this.bootStrapServer;
    }

    public String getBootStrapServer2() {
        return this.bootStrapServer2;
    }

    public String getConsumerGroupId() {
        return this.consumerGroupId;
    }

    public Integer getMaxPollRecords() {
        return this.maxPollRecords;
    }

    public Integer getAutoCommitInterval() {
        return this.autoCommitInterval;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setBootStrapServer(String bootStrapServer) {
        this.bootStrapServer = bootStrapServer;
    }

    public void setBootStrapServer2(String bootStrapServer2) {
        this.bootStrapServer2 = bootStrapServer2;
    }

    public void setConsumerGroupId(String consumerGroupId) {
        this.consumerGroupId = consumerGroupId;
    }

    public void setMaxPollRecords(Integer maxPollRecords) {
        this.maxPollRecords = maxPollRecords;
    }

    public void setAutoCommitInterval(Integer autoCommitInterval) {
        this.autoCommitInterval = autoCommitInterval;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BaseKafkaConfig)) {
            return false;
        } else {
            BaseKafkaConfig other = (BaseKafkaConfig)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$maxPollRecords = this.getMaxPollRecords();
                Object other$maxPollRecords = other.getMaxPollRecords();
                if (this$maxPollRecords == null) {
                    if (other$maxPollRecords != null) {
                        return false;
                    }
                } else if (!this$maxPollRecords.equals(other$maxPollRecords)) {
                    return false;
                }

                Object this$autoCommitInterval = this.getAutoCommitInterval();
                Object other$autoCommitInterval = other.getAutoCommitInterval();
                if (this$autoCommitInterval == null) {
                    if (other$autoCommitInterval != null) {
                        return false;
                    }
                } else if (!this$autoCommitInterval.equals(other$autoCommitInterval)) {
                    return false;
                }

                Object this$bootStrapServer = this.getBootStrapServer();
                Object other$bootStrapServer = other.getBootStrapServer();
                if (this$bootStrapServer == null) {
                    if (other$bootStrapServer != null) {
                        return false;
                    }
                } else if (!this$bootStrapServer.equals(other$bootStrapServer)) {
                    return false;
                }

                label62: {
                    Object this$bootStrapServer2 = this.getBootStrapServer2();
                    Object other$bootStrapServer2 = other.getBootStrapServer2();
                    if (this$bootStrapServer2 == null) {
                        if (other$bootStrapServer2 == null) {
                            break label62;
                        }
                    } else if (this$bootStrapServer2.equals(other$bootStrapServer2)) {
                        break label62;
                    }

                    return false;
                }

                label55: {
                    Object this$consumerGroupId = this.getConsumerGroupId();
                    Object other$consumerGroupId = other.getConsumerGroupId();
                    if (this$consumerGroupId == null) {
                        if (other$consumerGroupId == null) {
                            break label55;
                        }
                    } else if (this$consumerGroupId.equals(other$consumerGroupId)) {
                        break label55;
                    }

                    return false;
                }

                Object this$clientId = this.getClientId();
                Object other$clientId = other.getClientId();
                if (this$clientId == null) {
                    if (other$clientId != null) {
                        return false;
                    }
                } else if (!this$clientId.equals(other$clientId)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof BaseKafkaConfig;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $maxPollRecords = this.getMaxPollRecords();
        result = result * 59 + ($maxPollRecords == null ? 43 : $maxPollRecords.hashCode());
        Object $autoCommitInterval = this.getAutoCommitInterval();
        result = result * 59 + ($autoCommitInterval == null ? 43 : $autoCommitInterval.hashCode());
        Object $bootStrapServer = this.getBootStrapServer();
        result = result * 59 + ($bootStrapServer == null ? 43 : $bootStrapServer.hashCode());
        Object $bootStrapServer2 = this.getBootStrapServer2();
        result = result * 59 + ($bootStrapServer2 == null ? 43 : $bootStrapServer2.hashCode());
        Object $consumerGroupId = this.getConsumerGroupId();
        result = result * 59 + ($consumerGroupId == null ? 43 : $consumerGroupId.hashCode());
        Object $clientId = this.getClientId();
        result = result * 59 + ($clientId == null ? 43 : $clientId.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = this.getBootStrapServer();
        return "BaseKafkaConfig(bootStrapServer=" + var10000 + ", bootStrapServer2=" + this.getBootStrapServer2() + ", consumerGroupId=" + this.getConsumerGroupId() + ", maxPollRecords=" + this.getMaxPollRecords() + ", autoCommitInterval=" + this.getAutoCommitInterval() + ", clientId=" + this.getClientId() + ")";
    }
}
