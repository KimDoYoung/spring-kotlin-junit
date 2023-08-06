package kr.co.kalpa.demo.springkotlinjunit

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/hello")
class HelloController {

    @GetMapping
    fun helloWorld(): String{
        return "hello world"
    }
}