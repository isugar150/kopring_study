package com.example.study1.component.kafka

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service


@Service
class KafkaProducerService(
    val kafkaTemplate: KafkaTemplate<String, String>
) {

    fun sendMessageToKafka(message: String, topicName: String) {
        System.out.printf("Producer Message : %s%n", message)
        kafkaTemplate!!.send(topicName, message)
    }
}