package com.example.kafkaexample.controllers;

import com.example.kafkaexample.entity.ProducerRestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/publicMessage")
public class ProducerKafkaController {
    private final ObjectMapper objectMapper;
    private final KafkaTemplate kafkaTemplate;
    public ProducerKafkaController(KafkaTemplate kafkaTemplate, ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }
    @PostMapping("/")
    public ResponseEntity<?> publicMessage(@RequestBody ProducerRestModel producerRestModel){
        try {
            kafkaTemplate.send(producerRestModel.getTopic(), objectMapper.writeValueAsString(producerRestModel.getData()));
            System.out.println("send success, message : " +  objectMapper.writeValueAsString(producerRestModel.getData()));
            return new ResponseEntity<>(producerRestModel, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/send-dynamic")
    public ResponseEntity<?> publicMessageDynamic(@RequestBody ProducerRestModel producerRestModel) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, producerRestModel.getHost());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(props);
        KafkaTemplate<String, String> kafkaTemplateDynamic = new KafkaTemplate<>(producerFactory);


        try {
            kafkaTemplateDynamic.send(producerRestModel.getTopic(), objectMapper.writeValueAsString(producerRestModel.getData()));
            System.out.println("send success, message : " +  objectMapper.writeValueAsString(producerRestModel.getData()));
            return new ResponseEntity<>(producerRestModel, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } finally {
            kafkaTemplateDynamic.destroy();
        }


    }
}
