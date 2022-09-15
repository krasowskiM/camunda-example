package de.allianzdirect.springquoteservice.camunda.subscriber

import com.fasterxml.jackson.databind.ObjectMapper
import de.allianzdirect.springquoteservice.communication.QuoteRequest
import de.allianzdirect.springquoteservice.exception.QuoteScoreTooLow
import de.allianzdirect.springquoteservice.service.APPLICATION_PREVIOUSLY_REJECTED
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskHandler
import org.camunda.bpm.client.task.ExternalTaskService
import org.camunda.bpm.engine.variable.Variables
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@ExternalTaskSubscription("t_check_rejections")
class PastRejectionChecker : ExternalTaskHandler {
    private val objectMapper = ObjectMapper()
    private val logger = LoggerFactory.getLogger(PastRejectionChecker::class.java)

    override fun execute(p0: ExternalTask?, p1: ExternalTaskService?) {
        logger.info("checking previous rejections")
        p0?.allVariables?.apply {
            logger.info("process variables: ${objectMapper.writeValueAsString(this)}")
            p1?.complete(p0, Variables.createVariables().apply {
                val quoteRequest = ModelMapper().map(p0.allVariables?.get("quoteRequest"), QuoteRequest::class.java)
                val rejected = quoteRequest.name == "rejected"
                this["rejected"] = rejected
                logger.info("application rejected previously: $rejected")
                if (rejected) {
                    this["code"] = APPLICATION_PREVIOUSLY_REJECTED
                    logger.info("sending code 499 - application previously rejected")
                }
            })
        }

    }
}
