package com.example.study1.dto.common

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.util.Objects


data class RestResult(
    var success: Boolean = false,
    var message: String = "",
    var data: HashMap<String, Any> = HashMap()
) {

}