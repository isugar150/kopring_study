package com.example.study1.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.view.RedirectView

@Controller
class HomeController() {

    @GetMapping(value = ["/", "/docs"])
    fun root(): RedirectView {
        return RedirectView("/swagger-ui/index.html")
    }


}