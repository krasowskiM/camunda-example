package de.allianzdirect.springquoteservice.camunda.integration

import com.fasterxml.jackson.databind.ObjectMapper
import org.camunda.bpm.engine.ProcessEngine
import org.camunda.bpm.engine.runtime.ProcessInstantiationBuilder
import org.camunda.community.rest.client.api.*
import org.camunda.community.rest.impl.RemoteRuntimeService

class RemoteCamundaRuntimeService(
    processInstanceApiClient: ProcessInstanceApiClient,
    processDefinitionApiClient: ProcessDefinitionApiClient,
    messageApiClient: MessageApiClient,
    signalApiClient: SignalApiClient,
    executionApiClient: ExecutionApiClient,
    incidentApiClient: IncidentApiClient,
    processEngine: ProcessEngine,
    objectMapper: ObjectMapper
): RemoteRuntimeService(processInstanceApiClient,
    processDefinitionApiClient,
    messageApiClient,
    signalApiClient,
    executionApiClient,
    incidentApiClient,processEngine, objectMapper) {

//    override fun createProcessInstanceByKey(processDefinitionKey: String): ProcessInstantiationBuilder {
//
//    }
}