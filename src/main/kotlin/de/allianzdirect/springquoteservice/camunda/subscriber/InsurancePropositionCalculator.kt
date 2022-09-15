package de.allianzdirect.springquoteservice.camunda.subscriber

import de.allianzdirect.springquoteservice.service.PROCESS_SUCCESSFUL
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskHandler
import org.camunda.bpm.client.task.ExternalTaskService
import org.camunda.bpm.engine.variable.Variables
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import kotlin.math.log

@Component
@ExternalTaskSubscription("t_calculate_insurance_proposition")
class InsurancePropositionCalculator : ExternalTaskHandler {
    private val logger = LoggerFactory.getLogger(InsurancePropositionCalculator::class.java)

    override fun execute(p0: ExternalTask?, p1: ExternalTaskService?) {
        p1?.complete(p0, Variables.createVariables().apply {
            logger.info("process successful, calculating insurance proposition")
            this["code"] = PROCESS_SUCCESSFUL
        })
    }
}