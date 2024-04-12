package com.example.study1.controller

import com.example.study1.component.kafka.KafkaProducerService
import com.example.study1.dto.common.RestResult
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class HomeController {

    @Autowired
    lateinit var kafkaProducerService: KafkaProducerService

//    @GetMapping(value = ["/"])
//    fun root(request: HttpServletRequest): RedirectView {
//        return RedirectView("/swagger-ui/index.html")
//    }

    @GetMapping(value = ["/kafka"])
    fun root(request: HttpServletRequest): ResponseEntity<RestResult> {
        var result = RestResult()

        kafkaProducerService.sendMessageToKafka("TEST", "my-topic");

        result.success = true

        return ResponseEntity(result, HttpStatus.OK)
    }

}