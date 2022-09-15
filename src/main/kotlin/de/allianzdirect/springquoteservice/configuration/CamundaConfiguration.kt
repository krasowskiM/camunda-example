package de.allianzdirect.springquoteservice.configuration

import de.allianzdirect.springquoteservice.camunda.integration.HistoricVariableInstanceClient
import de.allianzdirect.springquoteservice.camunda.integration.RemoteInstanceAndVariableHistoryService
import org.camunda.community.rest.client.api.HistoricProcessInstanceApiClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CamundaConfiguration {
    @Bean("instance_variable_history")
    fun variableHistoryService(
        historicProcessInstanceApiClient: HistoricProcessInstanceApiClient,
        historicVariableInstanceApiClient: HistoricVariableInstanceClient
    ) = RemoteInstanceAndVariableHistoryService(historicProcessInstanceApiClient, historicVariableInstanceApiClient)
}