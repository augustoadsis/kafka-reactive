package com.augusto.kafkareactive.producer;

import com.augusto.kafkareactive.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/producer")
@Slf4j
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @RequestMapping(method = RequestMethod.POST)
    public void send(@RequestBody Message message) {
        producerService.send(message);
    }
}