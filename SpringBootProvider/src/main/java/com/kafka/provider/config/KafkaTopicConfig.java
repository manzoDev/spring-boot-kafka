package com.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic generateTopic(){

        Map<String, String> configuration = new HashMap<>();

        // delete (borra el mensaje), compact (mantiene el mas actual)
        configuration.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
        // tiempo que se retencion de mensajes - defecto (-1)
        configuration.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");
        // tamanio maximo del segmento - 1GB
        configuration.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");
        // tamanio maximo de cada mensaje - defecto (1MB)
        configuration.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012");

        return TopicBuilder.name("manzo-Dev")
                .partitions(2)
                .replicas(2)
                .configs(configuration)
                .build();
    }
}
