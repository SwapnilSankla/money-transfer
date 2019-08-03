package com.swapnilsankla.moneytransfer.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/hello")
class HelloController {

    @Get("/")
    fun get() = "Hello World !!!"
}