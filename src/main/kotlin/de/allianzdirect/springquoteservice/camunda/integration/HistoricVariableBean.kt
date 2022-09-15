package de.allianzdirect.springquoteservice.camunda.integration

import org.camunda.bpm.engine.history.HistoricVariableInstance
import org.camunda.bpm.engine.variable.value.TypedValue
import org.camunda.community.rest.client.model.HistoricVariableInstanceDto
import java.util.*

data class HistoricVariableBean(
    val id: String,
    val processDefinitionKey: String?,
    val processDefinitionId: String?,
    val processInstanceId: String?,
    val removalTime: Date?,
    val endActivityId: String?,
    val rootProcessInstanceId: String?,
    val caseInstanceId: String?,
    val tenantId: String?,
    val state: String?,
    val value: Any?,
    val variableName: String?,
    val executionId: String?,
    val activityInstanceId: String?,
    val caseDefinitionKey: String?,
    val caseDefinitionId: String?,
    val caseExecutionId: String?,
    val taskId: String?,
    val errorMessage: String?,
    val typeName: String?,
    val typedValue: TypedValue?,
    val variableTypeName: String?,
    val createTime: Date?
) {

    companion object {
        @JvmStatic
        fun fromHistoricVariableInstanceDto(variableInstance: HistoricVariableInstanceDto) =
            HistoricVariableBean(
                id = variableInstance.id,
                processDefinitionKey = variableInstance.processDefinitionKey,
                processDefinitionId = variableInstance.processDefinitionId,
                processInstanceId = variableInstance.processInstanceId,
                removalTime = variableInstance.removalTime,
                endActivityId = null,
                rootProcessInstanceId = variableInstance.rootProcessInstanceId,
                caseInstanceId = variableInstance.caseInstanceId,
                tenantId = variableInstance.tenantId,
                state = when (variableInstance.state) {
                    "CREATED" -> HistoricVariableInstance.STATE_CREATED
                    "DELETED" -> HistoricVariableInstance.STATE_DELETED
                    else -> throw IllegalStateException("unknown state for historic variable instance")
                },
                value = variableInstance.value,
                variableName = variableInstance.name,
                executionId = variableInstance.executionId,
                activityInstanceId = variableInstance.activityInstanceId,
                caseDefinitionKey = variableInstance.caseDefinitionKey,
                caseDefinitionId = variableInstance.caseDefinitionId,
                caseExecutionId = variableInstance.caseExecutionId,
                taskId = variableInstance.taskId,
                errorMessage = variableInstance.errorMessage,
                typeName = variableInstance.type,
                typedValue = null,
                variableTypeName = variableInstance.type,
                createTime = variableInstance.createTime
            )
    }
}