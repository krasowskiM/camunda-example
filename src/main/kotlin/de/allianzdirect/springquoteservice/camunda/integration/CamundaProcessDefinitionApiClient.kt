package de.allianzdirect.springquoteservice.camunda.integration

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.camunda.community.rest.client.model.BatchDto
import org.camunda.community.rest.client.model.ExceptionDto
import org.camunda.community.rest.client.model.ProcessInstanceWithVariablesDto
import org.camunda.community.rest.client.model.VariableValueDto
import org.openapitools.configuration.ClientConfiguration
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.context.annotation.Primary
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Primary
@FeignClient(
    name = "\${feign.client.config.camundaProcessDefinitionApi.name:camundaProcessDefinitionClient}",
    url = "\${feign.client.config.camundaProcessDefinitionApi.url:http://localhost:8080/engine-rest}",
    configuration = [ClientConfiguration::class]
)
interface CamundaProcessDefinitionApiClient : CamundaProcessDefinitionApi

@Tag(name = "CamundaProcessDefinition", description = "CamundaProcessDefinition API")
interface CamundaProcessDefinitionApi {

    @Operation(
        operationId = "createProcessInstanceByKeyOperation",
        summary = "Create Process Instance By Key",
        tags = ["Process Instance"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "Request successful.",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = BatchDto::class))]
        ), ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ExceptionDto::class))]
        ), ApiResponse(
            responseCode = "500",
            description = "The creation cannot be performed, for example because it starts a failing activity.",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ExceptionDto::class))]
        )]
    )
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/process-definition/key/{key}/start"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createProcessInstanceByKey(
        @Parameter(
            name = "key",
            description = "The id of the process instance to modify.",
            required = true
        ) @PathVariable("key") key: String?,
        @Parameter(
            name = "StartingProcessInstanceDto",
            description = ""
        ) @RequestBody(required = false) startingProcessInstanceDto: StartingProcessInstanceDto?
    ): ResponseEntity<ProcessInstanceWithVariablesDto?>?
}

data class StartingProcessInstanceDto(
    var variables: Map<String?, VariableValueDto?>? = null,
    var businessKey: String? = null,
    var withVariablesInReturn: Boolean? = false
)
