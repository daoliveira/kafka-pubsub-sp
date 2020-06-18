package com.daoliveira.kafkapubsubsb.services;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductPublisher {

    private static final Logger logger = LoggerFactory.getLogger(ProductPublisher.class);
    private static final String TOPIC = "products";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        logger.info(String.format("Publishing message: %s", message));
        kafkaTemplate.send(TOPIC, UUID.randomUUID().toString(), message);
    }
}