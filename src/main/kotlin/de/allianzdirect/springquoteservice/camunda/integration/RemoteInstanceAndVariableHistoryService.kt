package de.allianzdirect.springquoteservice.camunda.integration

import org.camunda.bpm.engine.history.HistoricVariableInstanceQuery
import org.camunda.community.rest.adapter.AbstractHistoryServiceAdapter
import org.camunda.community.rest.client.api.HistoricProcessInstanceApiClient
import org.camunda.community.rest.impl.query.DelegatingHistoricProcessInstanceQuery


class RemoteInstanceAndVariableHistoryService(
    private val historicProcessInstanceApiClient: HistoricProcessInstanceApiClient,
    private val historicVariableInstanceApiClient: HistoricVariableInstanceClient
) : AbstractHistoryServiceAdapter() {
    override fun createHistoricProcessInstanceQuery() =
        DelegatingHistoricProcessInstanceQuery(historicProcessInstanceApiClient)

    override fun createHistoricVariableInstanceQuery(): HistoricVariableInstanceQuery =
        DelegatingHistoricVariableInstanceQuery(historicVariableInstanceApiClient)
}