package com.kafka.jms.consumer.kafka_to_activemq.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.basedomains.base_domains.dto.OrderEvent;

@Service
public class OrderConsumer {
    
    @Autowired
    private JmsTemplate jmsTemplate;
        private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);
        @KafkaListener(topics = "${spring.kafka.topic.name}",groupId = "${spring.kafka.consumer.group-id}")
        public void consume(OrderEvent event) {
            LOGGER.info(String.format("Order Event consumed in kafka to  jms  service ==> %s", event.toString()));
            //save order event to database
             // Publish to JMS queues
        jmsTemplate.convertAndSend("stock-queue", event);
        jmsTemplate.convertAndSend("email-queue", event);

    }
}
