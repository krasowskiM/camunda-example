//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package de.allianzdirect.springquoteservice.camunda.integration

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.camunda.community.rest.client.model.CountResultDto
import org.camunda.community.rest.client.model.ExceptionDto
import org.camunda.community.rest.client.model.HistoricVariableInstanceDto
import org.camunda.community.rest.client.model.HistoricVariableInstanceQueryDto
import org.openapitools.configuration.ClientConfiguration
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.context.annotation.Primary
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Primary
@FeignClient(
    name = "\${feign.client.config.historicVariableInstanceClient.name:historicVariableInstanceClient}",
    url = "\${feign.client.config.historicVariableInstanceClient.url:http://localhost:8080/engine-rest}",
    configuration = [ClientConfiguration::class]
)
interface HistoricVariableInstanceClient : HistoricVariableInstanceApi

@Tag(name = "HistoricVariableInstance", description = "the HistoricVariableInstance API")
interface HistoricVariableInstanceApi {
    @Operation(
        operationId = "deleteHistoricVariableInstance",
        summary = "Delete Variable Instance",
        tags = ["Historic Variable Instance"],
        responses = [ApiResponse(
            responseCode = "204",
            description = "Request successful. This method returns no content."
        ), ApiResponse(
            responseCode = "404",
            description = "Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.17/reference/rest/overview/#error-handling) for the error response format.",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ExceptionDto::class))]
        )]
    )
    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/history/variable-instance/{id}"],
        produces = ["application/json"]
    )
    fun deleteHistoricVariableInstance(
        @Parameter(
            name = "id",
            description = "The id of the variable instance.",
            required = true
        ) @PathVariable("id") var1: String?
    ): ResponseEntity<Void?>?

    @Operation(
        operationId = "getHistoricVariableInstance",
        summary = "Get Variable Instance",
        tags = ["Historic Variable Instance"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "Request successful.",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = HistoricVariableInstanceDto::class)
            )]
        ), ApiResponse(
            responseCode = "404",
            description = "Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.17/reference/rest/overview/#error-handling) for the error response format.",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ExceptionDto::class))]
        )]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/history/variable-instance/{id}"],
        produces = ["application/json"]
    )
    fun getHistoricVariableInstance(
        @Parameter(
            name = "id",
            description = "The id of the variable instance.",
            required = true
        ) @PathVariable("id") var1: String?,
        @Parameter(
            name = "deserializeValues",
            description = "Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default `true`).  If set to `true`, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson's](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API's classpath.  If set to `false`, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While `true` is the default value for reasons of backward compatibility, we recommend setting this parameter to `false` when developing web applications that are independent of the Java process applications deployed to the engine."
        ) @RequestParam(value = "deserializeValues", required = false) var2: Boolean?
    ): ResponseEntity<HistoricVariableInstanceDto?>?

    @Operation(
        operationId = "getHistoricVariableInstanceBinary",
        summary = "Get Variable Instance (Binary)",
        tags = ["Historic Variable Instance"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "Request successful.",
            content = [Content(
                mediaType = "application/octet-stream",
                schema = Schema(implementation = Resource::class)
            ), Content(
                mediaType = "*/*",
                schema = Schema(implementation = Resource::class)
            ), Content(mediaType = "application/json", schema = Schema(implementation = Resource::class))]
        ), ApiResponse(
            responseCode = "400",
            description = "Variable with given id exists but is not a binary variable. See the [Introduction](https://docs.camunda.org/manual/7.17/reference/rest/overview/#error-handling) for the error response format.",
            content = [Content(
                mediaType = "application/octet-stream",
                schema = Schema(implementation = ExceptionDto::class)
            ), Content(
                mediaType = "*/*",
                schema = Schema(implementation = ExceptionDto::class)
            ), Content(mediaType = "application/json", schema = Schema(implementation = ExceptionDto::class))]
        ), ApiResponse(
            responseCode = "404",
            description = "Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.17/reference/rest/overview/#error-handling) for the error response format.",
            content = [Content(
                mediaType = "application/octet-stream",
                schema = Schema(implementation = ExceptionDto::class)
            ), Content(
                mediaType = "*/*",
                schema = Schema(implementation = ExceptionDto::class)
            ), Content(mediaType = "application/json", schema = Schema(implementation = ExceptionDto::class))]
        )]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/history/variable-instance/{id}/data"],
        produces = ["application/json"]
    )
    fun getHistoricVariableInstanceBinary(
        @Parameter(
            name = "id",
            description = "The id of the variable instance.",
            required = true
        ) @PathVariable("id") var1: String?
    ): ResponseEntity<Resource?>?

    @Operation(
        operationId = "getHistoricVariableInstances",
        summary = "Get Variable Instances",
        tags = ["Historic Variable Instance"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "Request successful.",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = HistoricVariableInstanceDto::class)
            )]
        ), ApiResponse(
            responseCode = "400",
            description = "Returned if some of the query parameters are invalid, for example if a `sortOrder` parameter is supplied, but no `sortBy`. See the [Introduction](https://docs.camunda.org/manual/7.17/reference/rest/overview/#error-handling) for the error response format.",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ExceptionDto::class))]
        )]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/history/variable-instance"],
        produces = ["application/json"]
    )
    fun getHistoricVariableInstances(
        @Parameter(
            name = "variableName",
            description = "Filter by variable name."
        ) @RequestParam(value = "variableName", required = false) var1: String?,
        @Parameter(
            name = "variableNameLike",
            description = "Restrict to variables with a name like the parameter."
        ) @RequestParam(value = "variableNameLike", required = false) var2: String?,
        @Parameter(
            name = "variableValue",
            description = "Filter by variable value. Is treated as a `String` object on server side."
        ) @RequestParam(value = "variableValueLike", required = false) var3: Any?,
        @Parameter(
            name = "variableNamesIgnoreCase",
            description = "Match the variable name provided in `variableName` and `variableNameLike` case- insensitively. If set to `true` **variableName** and **variablename** are treated as equal."
        ) @RequestParam(value = "variableNamesIgnoreCase", required = false) var4: Boolean?,
        @Parameter(
            name = "variableValuesIgnoreCase",
            description = "Match the variable value provided in `variableValue` case-insensitively. If set to `true` **variableValue** and **variablevalue** are treated as equal."
        ) @RequestParam(value = "variableValuesIgnoreCase", required = false) var5: Boolean?,
        @Parameter(
            name = "variableTypeIn",
            description = "Only include historic variable instances which belong to one of the passed and comma- separated variable types. A list of all supported variable types can be found [here](https://docs.camunda.org/manual/7.17/user-guide/process-engine/variables/#supported-variable-values). **Note:** All non-primitive variables are associated with the type 'serializable'."
        ) @RequestParam(value = "variableTypeIn", required = false) var6: String?,
        @Parameter(
            name = "includeDeleted",
            description = "Include variables that has already been deleted during the execution."
        ) @RequestParam(value = "includeDeleted", required = false) var7: Boolean?,
        @Parameter(
            name = "processInstanceId",
            description = "Filter by the process instance the variable belongs to."
        ) @RequestParam(value = "processInstanceId", required = false) var8: String?,
        @Parameter(
            name = "processInstanceIdIn",
            description = "Only include historic variable instances which belong to one of the passed and comma-separated process instance ids."
        ) @RequestParam(value = "processInstanceIdIn", required = false) var9: String?,
        @Parameter(
            name = "processDefinitionId",
            description = "Filter by the process definition the variable belongs to."
        ) @RequestParam(value = "processDefinitionId", required = false) var10: String?,
        @Parameter(
            name = "processDefinitionKey",
            description = "Filter by a key of the process definition the variable belongs to."
        ) @RequestParam(value = "processDefinitionKey", required = false) var11: String?,
        @Parameter(
            name = "executionIdIn",
            description = "Only include historic variable instances which belong to one of the passed and and comma-separated execution ids."
        ) @RequestParam(value = "executionIdIn", required = false) var12: String?,
        @Parameter(
            name = "caseInstanceId",
            description = "Filter by the case instance the variable belongs to."
        ) @RequestParam(value = "caseInstanceId", required = false) var13: String?,
        @Parameter(
            name = "caseExecutionIdIn",
            description = "Only include historic variable instances which belong to one of the passed and and comma-separated case execution ids."
        ) @RequestParam(value = "caseExecutionIdIn", required = false) var14: String?,
        @Parameter(
            name = "caseActivityIdIn",
            description = "Only include historic variable instances which belong to one of the passed and and comma-separated case activity ids."
        ) @RequestParam(value = "caseActivityIdIn", required = false) var15: String?,
        @Parameter(
            name = "taskIdIn",
            description = "Only include historic variable instances which belong to one of the passed and and comma-separated task ids."
        ) @RequestParam(value = "taskIdIn", required = false) var16: String?,
        @Parameter(
            name = "activityInstanceIdIn",
            description = "Only include historic variable instances which belong to one of the passed and and comma-separated activity instance ids."
        ) @RequestParam(value = "activityInstanceIdIn", required = false) var17: String?,
        @Parameter(
            name = "tenantIdIn",
            description = "Only include historic variable instances which belong to one of the passed and comma- separated tenant ids."
        ) @RequestParam(value = "tenantIdIn", required = false) var18: String?,
        @Parameter(
            name = "withoutTenantId",
            description = "Only include historic variable instances that belong to no tenant. Value may only be `true`, as `false` is the default behavior."
        ) @RequestParam(value = "withoutTenantId", required = false) var19: Boolean?,
        @Parameter(
            name = "variableNameIn",
            description = "Only include historic variable instances which belong to one of the passed and comma-separated variable names."
        ) @RequestParam(value = "variableNameIn", required = false) var20: String?,
        @Parameter(
            name = "sortBy",
            description = "Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter."
        ) @RequestParam(value = "sortBy", required = false) var21: String?,
        @Parameter(
            name = "sortOrder",
            description = "Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter."
        ) @RequestParam(value = "sortOrder", required = false) var22: String?,
        @Parameter(
            name = "firstResult",
            description = "Pagination of results. Specifies the index of the first result to return."
        ) @RequestParam(value = "firstResult", required = false) var23: Int?,
        @Parameter(
            name = "maxResults",
            description = "Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left."
        ) @RequestParam(value = "maxResults", required = false) var24: Int?,
        @Parameter(
            name = "deserializeValues",
            description = "Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default `true`).  If set to `true`, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson's](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API's classpath.  If set to `false`, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While `true` is the default value for reasons of backward compatibility, we recommend setting this parameter to `false` when developing web applications that are independent of the Java process applications deployed to the engine."
        ) @RequestParam(value = "deserializeValues", required = false) var25: Boolean?
    ): ResponseEntity<List<HistoricVariableInstanceDto?>?>?

    @Operation(
        operationId = "getHistoricVariableInstancesCount",
        summary = "Get Variable Instance Count",
        tags = ["Historic Variable Instance"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "Request successful.",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = CountResultDto::class))]
        ), ApiResponse(
            responseCode = "400",
            description = "Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.17/reference/rest/overview/#error-handling) for the error response format.",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ExceptionDto::class))]
        )]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/history/variable-instance/count"],
        produces = ["application/json"]
    )
    fun getHistoricVariableInstancesCount(
        @Parameter(
            name = "variableName",
            description = "Filter by variable name."
        ) @RequestParam(value = "variableName", required = false) var1: String?,
        @Parameter(
            name = "variableNameLike",
            description = "Restrict to variables with a name like the parameter."
        ) @RequestParam(value = "variableNameLike", required = false) var2: String?,
        @Parameter(
            name = "variableValue",
            description = "Filter by variable value. Is treated as a `String` object on server side."
        ) @RequestParam(value = "", required = false) var3: Any?,
        @Parameter(
            name = "variableNamesIgnoreCase",
            description = "Match the variable name provided in `variableName` and `variableNameLike` case- insensitively. If set to `true` **variableName** and **variablename** are treated as equal."
        ) @RequestParam(value = "variableNamesIgnoreCase", required = false) var4: Boolean?,
        @Parameter(
            name = "variableValuesIgnoreCase",
            description = "Match the variable value provided in `variableValue` case-insensitively. If set to `true` **variableValue** and **variablevalue** are treated as equal."
        ) @RequestParam(value = "variableValuesIgnoreCase", required = false) var5: Boolean?,
        @Parameter(
            name = "variableTypeIn",
            description = "Only include historic variable instances which belong to one of the passed and comma- separated variable types. A list of all supported variable types can be found [here](https://docs.camunda.org/manual/7.17/user-guide/process-engine/variables/#supported-variable-values). **Note:** All non-primitive variables are associated with the type 'serializable'."
        ) @RequestParam(value = "variableTypeIn", required = false) var6: String?,
        @Parameter(
            name = "includeDeleted",
            description = "Include variables that has already been deleted during the execution."
        ) @RequestParam(value = "includeDeleted", required = false) var7: Boolean?,
        @Parameter(
            name = "processInstanceId",
            description = "Filter by the process instance the variable belongs to."
        ) @RequestParam(value = "processInstanceId", required = false) var8: String?,
        @Parameter(
            name = "processInstanceIdIn",
            description = "Only include historic variable instances which belong to one of the passed and comma-separated process instance ids."
        ) @RequestParam(value = "processInstanceIdIn", required = false) var9: String?,
        @Parameter(
            name = "processDefinitionId",
            description = "Filter by the process definition the variable belongs to."
        ) @RequestParam(value = "processDefinitionId", required = false) var10: String?,
        @Parameter(
            name = "processDefinitionKey",
            description = "Filter by a key of the process definition the variable belongs to."
        ) @RequestParam(value = "processDefinitionKey", required = false) var11: String?,
        @Parameter(
            name = "executionIdIn",
            description = "Only include historic variable instances which belong to one of the passed and and comma-separated execution ids."
        ) @RequestParam(value = "executionIdIn", required = false) var12: String?,
        @Parameter(
            name = "caseInstanceId",
            description = "Filter by the case instance the variable belongs to."
        ) @RequestParam(value = "caseInstanceId", required = false) var13: String?,
        @Parameter(
            name = "caseExecutionIdIn",
            description = "Only include historic variable instances which belong to one of the passed and and comma-separated case execution ids."
        ) @RequestParam(value = "caseExecutionIdIn", required = false) var14: String?,
        @Parameter(
            name = "caseActivityIdIn",
            description = "Only include historic variable instances which belong to one of the passed and and comma-separated case activity ids."
        ) @RequestParam(value = "caseActivityIdIn", required = false) var15: String?,
        @Parameter(
            name = "taskIdIn",
            description = "Only include historic variable instances which belong to one of the passed and and comma-separated task ids."
        ) @RequestParam(value = "taskIdIn", required = false) var16: String?,
        @Parameter(
            name = "activityInstanceIdIn",
            description = "Only include historic variable instances which belong to one of the passed and and comma-separated activity instance ids."
        ) @RequestParam(value = "activityInstanceIdIn", required = false) var17: String?,
        @Parameter(
            name = "tenantIdIn",
            description = "Only include historic variable instances which belong to one of the passed and comma- separated tenant ids."
        ) @RequestParam(value = "tenantIdIn", required = false) var18: String?,
        @Parameter(
            name = "withoutTenantId",
            description = "Only include historic variable instances that belong to no tenant. Value may only be `true`, as `false` is the default behavior."
        ) @RequestParam(value = "withoutTenantId", required = false) var19: Boolean?,
        @Parameter(
            name = "variableNameIn",
            description = "Only include historic variable instances which belong to one of the passed and comma-separated variable names."
        ) @RequestParam(value = "variableNameIn", required = false) var20: String?
    ): ResponseEntity<CountResultDto?>?

    @Operation(
        operationId = "queryHistoricVariableInstances",
        summary = "Get Variable Instances (POST)",
        tags = ["Historic Variable Instance"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "Request successful.",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = HistoricVariableInstanceDto::class)
            )]
        ), ApiResponse(
            responseCode = "400",
            description = "Returned if some of the query parameters are invalid, for example if a `sortOrder` parameter is supplied, but no `sortBy`. See the [Introduction](https://docs.camunda.org/manual/7.17/reference/rest/overview/#error-handling) for the error response format.",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ExceptionDto::class))]
        )]
    )
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/history/variable-instance"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun queryHistoricVariableInstances(
        @Parameter(
            name = "firstResult",
            description = "Pagination of results. Specifies the index of the first result to return."
        ) @RequestParam(value = "firstResult", required = false) var1: Int?,
        @Parameter(
            name = "maxResults",
            description = "Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left."
        ) @RequestParam(value = "maxResults", required = false) var2: Int?,
        @Parameter(
            name = "deserializeValues",
            description = "Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default `true`).  If set to `true`, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson's](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API's classpath.  If set to `false`, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While `true` is the default value for reasons of backward compatibility, we recommend setting this parameter to `false` when developing web applications that are independent of the Java process applications deployed to the engine."
        ) @RequestParam(value = "deserializeValues", required = false) var3: Boolean?,
        @Parameter(
            name = "HistoricVariableInstanceQueryDto",
            description = ""
        ) @RequestBody(required = false) var4: HistoricVariableInstanceQueryDto?
    ): ResponseEntity<List<HistoricVariableInstanceDto?>?>?

    @Operation(
        operationId = "queryHistoricVariableInstancesCount",
        summary = "Get Variable Instance Count (POST)",
        tags = ["Historic Variable Instance"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "Request successful.",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = CountResultDto::class))]
        ), ApiResponse(
            responseCode = "400",
            description = "Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.17/reference/rest/overview/#error-handling) for the error response format.",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ExceptionDto::class))]
        )]
    )
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/history/variable-instance/count"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun queryHistoricVariableInstancesCount(
        @Parameter(
            name = "HistoricVariableInstanceQueryDto",
            description = ""
        ) @RequestBody(required = false) var1: HistoricVariableInstanceQueryDto?
    ): ResponseEntity<CountResultDto?>?
}