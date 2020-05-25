package com.augusto.kafkareactive.producer;

import com.augusto.kafkareactive.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ProducerService {

    private static final String TOPIC = "testTopic";

    @Autowired
    private KafkaTemplate kafkaTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public Mono<Void> send(final Message message) {
        String payload = toBinary(message);
        log.info(payload);
        kafkaTemplate.send(TOPIC, "key", payload);
        return Mono.empty();
    }

    private String toBinary(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}