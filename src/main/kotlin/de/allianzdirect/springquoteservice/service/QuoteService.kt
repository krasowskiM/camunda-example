package de.allianzdirect.springquoteservice.service

import com.fasterxml.jackson.databind.ObjectMapper
import de.allianzdirect.springquoteservice.camunda.integration.CamundaProcessDefinitionApiClient
import de.allianzdirect.springquoteservice.camunda.integration.StartingProcessInstanceDto
import de.allianzdirect.springquoteservice.communication.QuoteRequest
import de.allianzdirect.springquoteservice.communication.QuoteResponse
import de.allianzdirect.springquoteservice.exception.ApplicationPreviouslyRejected
import de.allianzdirect.springquoteservice.exception.QuoteScoreTooLow
import org.camunda.bpm.engine.HistoryService
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.history.HistoricVariableInstance
import org.camunda.community.rest.client.model.VariableValueDto
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

const val QUOTATION_SCORE_TOO_LOW = 470
const val APPLICATION_PREVIOUSLY_REJECTED = 499
const val PROCESS_SUCCESSFUL = 201

@Service
class QuoteService(
    @field:Qualifier("remote") private val runtimeService: RuntimeService,
    @Qualifier("instance_variable_history") private val historyVariableService: HistoryService,
//    private val processDefinitionApiClient: CamundaProcessDefinitionApiClient
    private val objectMapper: ObjectMapper
) {
    private val logger = LoggerFactory.getLogger(QuoteService::class.java)

    fun processQuote(quoteRequest: QuoteRequest): QuoteResponse {
        logger.info("starting process instance")
        val processInstance = runtimeService.startProcessInstanceByKey(
            "calculate_insurance_proposition",
            mapOf("quoteRequest" to quoteRequest)
        )
        logger.info("awaiting process completion...")
        awaitForProcessToComplete(processInstance.processInstanceId)
        val processVariables = getProcessVariables(processInstance.processInstanceId)
        val quoteResponse = processVariables?.firstOrNull { it.name == "quoteResponse" }
        return when (processVariables?.firstOrNull { it.name == "code" }?.value) {
            QUOTATION_SCORE_TOO_LOW -> throw QuoteScoreTooLow
            APPLICATION_PREVIOUSLY_REJECTED -> throw ApplicationPreviouslyRejected
            PROCESS_SUCCESSFUL -> objectMapper.readValue(quoteResponse?.value.toString(), QuoteResponse::class.java)
            else -> throw RuntimeException("unknown status code")
        }
    }

    private fun getProcessVariables(processInstanceId: String): MutableList<HistoricVariableInstance>? =
        historyVariableService.createHistoricVariableInstanceQuery()
            .processInstanceId(processInstanceId).variableNameIn("code", "quoteResponse").list()

    private fun awaitForProcessToComplete(processInstanceId: String) {
        while (historyVariableService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .completed().singleResult() == null
        ) {
            Thread.sleep(500)
        }
    }
}