package de.allianzdirect.springquoteservice.api

import de.allianzdirect.springquoteservice.communication.QuoteRequest
import de.allianzdirect.springquoteservice.communication.QuoteResponse
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse

@OpenAPIDefinition(
    info = Info(title = "Quote service API", description = "Api providing functions for customer quote")
)
interface QuoteApi {

    @Operation(
        responses = [
            ApiResponse(
                description = "customer data is enriched with additional information",
                responseCode = "201"
            ),
            ApiResponse(
                description = "customer quotation score is less than required",
                responseCode = "470",
                content = [Content(schema = Schema(hidden = true))]
            ),
            ApiResponse(
                description = "there were rejected applications for this customer",
                responseCode = "499",
                content = [Content(schema = Schema(hidden = true))]
            )
        ]
    )
    fun processQuote(quoteRequest: QuoteRequest): QuoteResponse
}