package de.allianzdirect.springquoteservice.camunda.subscriber

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskHandler
import org.camunda.bpm.client.task.ExternalTaskService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@ExternalTaskSubscription("t_process_completed")
class ProcessCompletedListener : ExternalTaskHandler {
    private val logger = LoggerFactory.getLogger(ProcessCompletedListener::class.java)

    override fun execute(externalTask: ExternalTask?, externalTaskService: ExternalTaskService?) {
        logger.info("process ${externalTask?.processInstanceId} completed")
        logger.info("variables: ${externalTask?.allVariables.toString()}")
        externalTaskService?.complete(externalTask)
    }
}