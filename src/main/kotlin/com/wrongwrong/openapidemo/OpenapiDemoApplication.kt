package com.wrongwrong.openapidemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration

// セキュリティは一旦無効化
@SpringBootApplication(exclude = [ SecurityAutoConfiguration::class ])
class OpenapiDemoApplication

fun main(args: Array<String>) {
    runApplication<OpenapiDemoApplication>(*args)
}
