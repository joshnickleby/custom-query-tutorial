package com.astontech.dbmap.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {

  @GetMapping("/")
  fun getIndex(): String = "Test"
}