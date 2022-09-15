package de.allianzdirect.springquoteservice.camunda.subscriber

import com.fasterxml.jackson.databind.ObjectMapper
import de.allianzdirect.springquoteservice.communication.QuoteRequest
import de.allianzdirect.springquoteservice.exception.ApplicationPreviouslyRejected
import de.allianzdirect.springquoteservice.service.QUOTATION_SCORE_TOO_LOW
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskHandler
import org.camunda.bpm.client.task.ExternalTaskService
import org.camunda.bpm.engine.variable.Variables
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

private const val QUOTATION_SCORE_THRESHOLD = 1000

@Component
@ExternalTaskSubscription("t_check_quotation_score")
class QuotationScoreCalculator(
    private val objectMapper: ObjectMapper
) : ExternalTaskHandler {
    private val logger = LoggerFactory.getLogger(QuotationScoreCalculator::class.java)

    override fun execute(p0: ExternalTask?, p1: ExternalTaskService?) {
        p1?.complete(p0, Variables.createVariables().apply {
            logger.info("process variables: ${objectMapper.writeValueAsString(p0?.allVariables)}")
            logger.info("calculating quotation score")
            val quoteRequest = ModelMapper().map(p0?.allVariables?.get("quoteRequest"), QuoteRequest::class.java)
            val quotationScore = if (quoteRequest.surname == "quote") { 2000 } else { 500 }
            this["quotationScore"] = quotationScore
            logger.info("quotation score is $quotationScore")
            if (quotationScore < QUOTATION_SCORE_THRESHOLD) {
                this["code"] = QUOTATION_SCORE_TOO_LOW
                logger.info("sending code 470 - quotation score too low")
            }
        })
    }
}