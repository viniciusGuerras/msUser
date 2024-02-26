package com.compassuol.sp.challenge.msuser.domain.rabbitMq;

import com.compassuol.sp.challenge.msuser.domain.exceptions.MessageConversionException;
import com.compassuol.sp.challenge.msuser.domain.model.Notification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitProducer {

        @Value("${rabbitmq.exchange.name}")
        private String exchange;

        @Value("${rabbitmq.routing.key}")
        private String routingKey;

        private static final Logger LOGGER = LoggerFactory.getLogger(RabbitProducer.class);

        private final RabbitTemplate rabbitTemplate;

        public RabbitProducer(RabbitTemplate rabbitTemplate) {
            this.rabbitTemplate = rabbitTemplate;
        }

        public void sendMessage(Notification dto){
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                byte[] payload = objectMapper.writeValueAsBytes(dto);
                LOGGER.info(String.format("Message sent -> %s", dto.getEvent()));
                rabbitTemplate.convertAndSend(exchange, routingKey, payload);
            }
            catch (JsonProcessingException ex){
                throw new MessageConversionException("unable to parse json object");
            }
        }
}
