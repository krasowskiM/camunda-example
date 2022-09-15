package de.allianzdirect.springquoteservice.camunda.integration

import org.camunda.bpm.engine.history.HistoricVariableInstance
import org.camunda.bpm.engine.variable.value.TypedValue
import java.util.*

class HistoricVariableInstanceAdapter(private val historicVariableInstanceBean: HistoricVariableBean) :
    HistoricVariableInstance {
    override fun getProcessDefinitionKey(): String? = historicVariableInstanceBean.processDefinitionKey
    override fun getRootProcessInstanceId(): String? = historicVariableInstanceBean.rootProcessInstanceId
    override fun getProcessInstanceId(): String? = historicVariableInstanceBean.processInstanceId
    override fun getExecutionId(): String? = historicVariableInstanceBean.executionId
    override fun getActivtyInstanceId(): String? = historicVariableInstanceBean.activityInstanceId
    override fun getActivityInstanceId(): String? = historicVariableInstanceBean.activityInstanceId
    override fun getCaseDefinitionKey(): String? = historicVariableInstanceBean.caseDefinitionKey
    override fun getCaseDefinitionId(): String? = historicVariableInstanceBean.caseDefinitionId
    override fun getProcessDefinitionId(): String? = historicVariableInstanceBean.processDefinitionId
    override fun getRemovalTime(): Date? = historicVariableInstanceBean.removalTime
    override fun getCaseInstanceId(): String? = historicVariableInstanceBean.caseInstanceId
    override fun getCaseExecutionId(): String? = historicVariableInstanceBean.caseExecutionId
    override fun getTaskId(): String? = historicVariableInstanceBean.taskId
    override fun getErrorMessage(): String? = historicVariableInstanceBean.errorMessage
    override fun getId(): String = historicVariableInstanceBean.id
    override fun getName(): String? = historicVariableInstanceBean.variableName
    override fun getTypeName(): String? = historicVariableInstanceBean.typeName
    override fun getValue(): Any? = historicVariableInstanceBean.value
    override fun getTypedValue(): TypedValue? = historicVariableInstanceBean.typedValue
    override fun getVariableName(): String? = historicVariableInstanceBean.variableName
    override fun getVariableTypeName(): String? = historicVariableInstanceBean.variableTypeName
    override fun getTenantId(): String? = historicVariableInstanceBean.tenantId
    override fun getState(): String? = historicVariableInstanceBean.state
    override fun getCreateTime(): Date? = historicVariableInstanceBean.createTime
}

