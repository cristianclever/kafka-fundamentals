package com.kafka.fundamentals.customer_management_service.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Slf4j
@Configuration
public class KafkaTopicConfig {


    @Bean
    public NewTopic customerRegisteredTopic() {
        String topicName = "customer-registered-topic";
        NewTopic t = TopicBuilder.name(topicName)
                .partitions(6)
                .replicas(1) // Como temos apenas 1 broker local, o fator de replicação deve ser 1
                .build();
        log.info("New topic {} created", topicName);
        return t;

    }

}
