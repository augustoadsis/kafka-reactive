package com.augusto.kafkareactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaReactiveApplication.class, args);
    }

}
