package com.daoliveira.kafkapubsubsb.tasks;

import java.util.Random;

import com.daoliveira.kafkapubsubsb.services.ProductPublisher;
import com.github.javafaker.Faker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CreateProductTask {

    //private static final Logger logger = LoggerFactory.getLogger(CreateProductTask.class);
    private static final String[] PROD_TYPE = new String[] {"Fragance", "Table Set", "Bedding", "Boots", "T-Shirt", "Heels"};

    @Autowired
    ProductPublisher productPublisher;
    
    @Scheduled(fixedDelayString = "${product.producing.delay}")
    public void run() {
        Faker prodFaker = new Faker();
        String prodName = prodFaker.funnyName().name() + " " + PROD_TYPE[new Random().nextInt(PROD_TYPE.length)];
        String msg = String.format("{ \"name\": \"%s\" }", prodName);
        productPublisher.sendMessage(msg);
    }
}