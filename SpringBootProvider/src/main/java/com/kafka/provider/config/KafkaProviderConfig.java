package com.kafka.provider.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProviderConfig {

    @Value("${spring.kafka.bootstrapServers}")
    private String bootstrapServers;

    public Map<String, Object> producerConfig(){

        Map<String, Object> properties = new HashMap<>();
        // indicamos el servidor de kafka
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        // convertira el string en bytes (para el envio por medio de kafka)
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // ahora serializamos el objeto
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return properties;
    }

    // crear un proveedor que nos ayude a enviar los mensajes
    @Bean
    public ProducerFactory<String, String> providerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    //
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
}
