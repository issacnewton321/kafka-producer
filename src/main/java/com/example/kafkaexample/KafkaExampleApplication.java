package com.example.kafkaexample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaExampleApplication.class, args);
	}

//	@Bean
//	CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate){
//		return args -> {
//			kafkaTemplate.send("CRM_UPGRADE_K_LEVEL", "{\"user_id\":\"191129176001009\",\"is_model\":1,\"sent_time\":1665464494512,\"context\":{\"kyc_level\":1,\"inactive_days\":5,\"inactive_type\":\"NO_MAP\",\"is_login_zpa\":0},\"suggestions\":[{\"content\":{\"encourageAction\":\"MAP_CARD\",\"requiredContents\":[\"TELCO\"]}}],\"uuid\":445537586848968}");
//		};
//	}

}
