package de.allianzdirect.springquoteservice.camunda.subscriber

import com.fasterxml.jackson.databind.ObjectMapper
import de.allianzdirect.springquoteservice.communication.Car
import de.allianzdirect.springquoteservice.communication.Owner
import de.allianzdirect.springquoteservice.communication.QuoteRequest
import de.allianzdirect.springquoteservice.communication.QuoteResponse
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskHandler
import org.camunda.bpm.client.task.ExternalTaskService
import org.camunda.bpm.engine.variable.Variables
import org.camunda.bpm.model.dmn.instance.Variable
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@ExternalTaskSubscription("t_enrich_with_additional_info")
class AdditionalInformationEnricher(
    private val objectMapper: ObjectMapper
): ExternalTaskHandler {
    private val logger = LoggerFactory.getLogger(AdditionalInformationEnricher::class.java)

    override fun execute(p0: ExternalTask?, p1: ExternalTaskService?) {
        logger.info("process variables: ${objectMapper.writeValueAsString(p0?.allVariables)}")
        p1?.complete(p0, Variables.fromMap(mutableMapOf(
            "quoteResponse" to ModelMapper().map(
                p0?.allVariables?.get("quoteRequest"),
                QuoteRequest::class.java
            ).let {
                logger.info("providing additional information on customer - street: test1, number: test2, city: test3")
                logger.info("providing additional information on car - engine power: 120hp")
                QuoteResponse(
                    Owner(it.name, it.surname, "test1", "test2", "test3"),
                    Car(it.registrationNumber, it.engineType, "120hp")
                )
            }
        ) as Map<String, Any>?))
    }
}
