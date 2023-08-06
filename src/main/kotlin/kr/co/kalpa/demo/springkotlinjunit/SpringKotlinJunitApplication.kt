package kr.co.kalpa.demo.springkotlinjunit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringKotlinJunitApplication

fun main(args: Array<String>) {
	runApplication<SpringKotlinJunitApplication>(*args)
}
