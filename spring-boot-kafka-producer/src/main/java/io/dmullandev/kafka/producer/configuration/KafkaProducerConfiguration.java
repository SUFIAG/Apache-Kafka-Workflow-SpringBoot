package io.dmullandev.kafka.producer.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import io.dmullandev.kafka.constants.KafkaAppConstants;
import io.dmullandev.kafka.model.BusinessObject;

@Configuration
public class KafkaProducerConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    @Bean
    public Map<String, Object> producerConfigsBusinessObject() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public ProducerFactory<String, BusinessObject> producerFactoryBusinessObject() {
        return new DefaultKafkaProducerFactory<>(producerConfigsBusinessObject());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public KafkaTemplate<String, BusinessObject> kafkaTemplateBusinessObject() {
        return new KafkaTemplate<>(producerFactoryBusinessObject());
    }

    @Bean
    public NewTopic topicTimestamp() {
        return TopicBuilder.name(KafkaAppConstants.APP_TOPIC_TIMESTAMP)
                           .build();
    }

    @Bean
    public NewTopic topicBusinessObject() {
        return TopicBuilder.name(KafkaAppConstants.APP_TOPIC_BUSINESSOBJECT)
                           .build();
    }

    @Bean
    public NewTopic topicWikimedia() {
        return TopicBuilder.name(KafkaAppConstants.APP_TOPIC_WIKIMEDIA)
                           .build();
    }

}
