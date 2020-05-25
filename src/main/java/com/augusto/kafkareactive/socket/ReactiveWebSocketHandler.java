package com.augusto.kafkareactive.socket;

import com.augusto.kafkareactive.Message;
import com.augusto.kafkareactive.consumer.ConsumerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

@Component
public class ReactiveWebSocketHandler implements WebSocketHandler {

    private static final ObjectMapper json = new ObjectMapper();

    @Autowired
    ConsumerService kafkaService;

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        return webSocketSession.send(kafkaService.consume()
                .map(record -> {
                    Message message = new Message("[Kafka] Add message", record.value());
                    try {
                        return json.writeValueAsString(message);
                    } catch (JsonProcessingException e) {
                        return "Error while serializing to JSON";
                    }
                })
                .map(webSocketSession::textMessage))
                .and(webSocketSession.receive()
                        .map(WebSocketMessage::getPayloadAsText).log());
    }
}
