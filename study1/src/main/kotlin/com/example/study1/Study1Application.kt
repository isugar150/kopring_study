package com.example.study1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
class Study1Application

fun main(args: Array<String>) {
    runApplication<Study1Application>(*args)
}
