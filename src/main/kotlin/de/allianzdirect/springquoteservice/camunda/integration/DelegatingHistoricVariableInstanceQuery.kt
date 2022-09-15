package de.allianzdirect.springquoteservice.camunda.integration

import mu.KLogging
import org.camunda.bpm.engine.ProcessEngineException
import org.camunda.bpm.engine.history.HistoricVariableInstance
import org.camunda.bpm.engine.impl.HistoricVariableInstanceQueryImpl
import org.camunda.community.rest.client.model.HistoricVariableInstanceQueryDto
import org.camunda.community.rest.impl.toHistoricProcessInstanceSorting
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

class DelegatingHistoricVariableInstanceQuery(private val historicVariableInstanceApiClient: HistoricVariableInstanceClient) :
    HistoricVariableInstanceQueryImpl() {

    companion object : KLogging()

    override fun list(): List<HistoricVariableInstance> =
        historicVariableInstanceApiClient.queryHistoricVariableInstances(
            this.firstResult,
            this.maxResults,
            false,
            fillQueryDto()
        )?.body!!.map {
            HistoricVariableInstanceAdapter(HistoricVariableBean.fromHistoricVariableInstanceDto(it!!))
        }

    override fun listPage(firstResult: Int, maxResults: Int): List<HistoricVariableInstance> =
        historicVariableInstanceApiClient.queryHistoricVariableInstances(
            firstResult,
            maxResults,
            false,
            fillQueryDto()
        )?.body!!.map {
            HistoricVariableInstanceAdapter(HistoricVariableBean.fromHistoricVariableInstanceDto(it!!))
        }

    override fun listIds(): List<String> {
        return list().map { it.id }
    }

    override fun unlimitedList(): List<HistoricVariableInstance> {
        return list()
    }

    override fun count(): Long =
        historicVariableInstanceApiClient.queryHistoricVariableInstancesCount(fillQueryDto())?.body!!.count

    override fun singleResult(): HistoricVariableInstance? {
        val results = list()
        return when {
            results.size == 1 -> results[0]
            results.size > 1 -> throw ProcessEngineException("Query return " + results.size.toString() + " results instead of max 1")
            else -> null
        }
    }

    override fun ensureVariablesInitialized() = Unit

    private fun fillQueryDto() = HistoricVariableInstanceQueryDto().apply {
        checkQueryOk()
        val dtoPropertiesByName =
            HistoricVariableInstanceQueryDto::class.memberProperties.filterIsInstance<KMutableProperty1<HistoricVariableInstanceQueryDto, Any?>>()
                .associateBy { it.name }
        val queryPropertiesByName = HistoricVariableInstanceQueryImpl::class.memberProperties.associateBy { it.name }
        dtoPropertiesByName.forEach {
            val valueToSet = when (it.key) {
                "variableName" -> this@DelegatingHistoricVariableInstanceQuery.variableName
                "variableNameLike" -> this@DelegatingHistoricVariableInstanceQuery.variableNameLike
                "variableNameIn" -> this@DelegatingHistoricVariableInstanceQuery.variableNameIn
                "variableValue" -> this@DelegatingHistoricVariableInstanceQuery.queryVariableValue?.value
                "variableNamesIgnoreCase" -> this@DelegatingHistoricVariableInstanceQuery.variableNamesIgnoreCase
                "variableValuesIgnoreCase" -> this@DelegatingHistoricVariableInstanceQuery.variableValuesIgnoreCase
                "variableTypeIn" -> this@DelegatingHistoricVariableInstanceQuery.variableTypes?.toList()
                "includeDeleted" -> this@DelegatingHistoricVariableInstanceQuery.includeDeleted
                "processInstanceId" -> this@DelegatingHistoricVariableInstanceQuery.processInstanceId
                "processInstanceIdIn" -> this@DelegatingHistoricVariableInstanceQuery.processInstanceIds?.toList()
                "processDefinitionId" -> this@DelegatingHistoricVariableInstanceQuery.processDefinitionId
                "processDefinitionKey" -> this@DelegatingHistoricVariableInstanceQuery.processDefinitionKey
                "executionIdIn" -> this@DelegatingHistoricVariableInstanceQuery.executionIds?.toList()
                "caseInstanceId" -> this@DelegatingHistoricVariableInstanceQuery.caseInstanceId
                "caseExecutionIdIn" -> this@DelegatingHistoricVariableInstanceQuery.caseExecutionIds?.toList()
                "caseActivityIdIn" -> this@DelegatingHistoricVariableInstanceQuery.caseActivityIds?.toList()
                "taskIdIn" -> this@DelegatingHistoricVariableInstanceQuery.taskIds?.toList()
                "activityInstanceIdIn" -> this@DelegatingHistoricVariableInstanceQuery.activityInstanceIds?.toList()
                "tenantIdIn" -> this@DelegatingHistoricVariableInstanceQuery.tenantIds?.toList()
                "withoutTenantId" -> this@DelegatingHistoricVariableInstanceQuery.isTenantIdSet && this@DelegatingHistoricVariableInstanceQuery.tenantIds == null
                "sorting" -> this@DelegatingHistoricVariableInstanceQuery.orderingProperties
                    .mapNotNull { orderingProperty -> orderingProperty.toHistoricProcessInstanceSorting() }
                    .filter { orderingProperty -> orderingProperty.sortBy != null }

                else -> {
                    val queryProperty = queryPropertiesByName[it.key]
                    if (queryProperty == null) {
                        throw IllegalArgumentException("no property found for ${it.key}")
                    } else if (!queryProperty.returnType.isSubtypeOf(it.value.returnType)) {
                        logger.warn { "${queryProperty.returnType} is not assignable to ${it.value.returnType} for ${it.key}" }
                        null
                    } else {
                        queryProperty.isAccessible = true
                        queryProperty.get(this@DelegatingHistoricVariableInstanceQuery)
                    }
                }
            }
            it.value.isAccessible = true
            it.value.set(this, valueToSet)
        }
    }

}