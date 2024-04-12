package com.example.study1.listener

import org.springframework.context.annotation.DependsOn
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import java.io.IOException

@Service
@DependsOn(value = ["KafkaConsumerConfig"])
class KafkaTestListener {

    @KafkaListener(topics = ["my-topic"], groupId = "consumer_group01")
    @Throws(IOException::class)
    fun consume(message: String?) {
        System.out.printf("Consumed Message : %s%n", message)
    }

}