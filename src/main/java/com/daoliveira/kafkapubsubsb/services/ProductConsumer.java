package com.daoliveira.kafkapubsubsb.services;

import com.daoliveira.kafkapubsubsb.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductConsumer {
    private final Logger logger = LoggerFactory.getLogger(ProductConsumer.class);

    @Value("${product.rest.endpoint}")
    private String productEndpoint;

    @Autowired
    RestTemplate restTemplate;

    @KafkaListener(topics = "products", groupId = "group-01")
    public void consume(String message) throws JsonMappingException, JsonProcessingException {
        logger.info("Consuming message: {}", message);
        Product product = new ObjectMapper().readValue(message, Product.class);
        Product createdProduct = restTemplate.postForObject(productEndpoint, product, Product.class);
        logger.info("POST response: {}", createdProduct);
    }
}