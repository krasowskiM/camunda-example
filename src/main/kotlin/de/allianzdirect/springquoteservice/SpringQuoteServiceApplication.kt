package de.allianzdirect.springquoteservice

import org.camunda.community.rest.EnableCamundaRestClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
@EnableCamundaRestClient
class SpringQuoteServiceApplication

fun main(args: Array<String>) {
    runApplication<SpringQuoteServiceApplication>(*args)
}
