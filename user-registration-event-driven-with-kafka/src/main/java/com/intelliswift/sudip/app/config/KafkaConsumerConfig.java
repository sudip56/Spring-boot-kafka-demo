package com.intelliswift.sudip.app.config;

import java.util.HashMap;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.intelliswift.sudip.app.DTO.StudentDTO;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
	
	 @Value(value = "${spring.kafka.bootstrap-servers}")
	    private String bootstrapAddress;
	 
	@Bean
	public ConsumerFactory<String, StudentDTO> consumerFactory() {

		HashMap<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
				new JsonDeserializer(StudentDTO.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, StudentDTO> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, StudentDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}
