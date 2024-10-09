package io.github.dabico.store

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class SpringBootStoreDemoApplication

fun main(args: Array<String>) {
    SpringApplication.run(SpringBootStoreDemoApplication::class.java, *args)
}
