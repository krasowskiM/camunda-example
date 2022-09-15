package de.allianzdirect.springquoteservice.controller

import de.allianzdirect.springquoteservice.api.QuoteApi
import de.allianzdirect.springquoteservice.communication.QuoteRequest
import de.allianzdirect.springquoteservice.communication.QuoteResponse
import de.allianzdirect.springquoteservice.service.QuoteService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/quoteApi/v1")
class QuoteController(
    private val quoteService: QuoteService
) : QuoteApi {

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    override fun processQuote(@RequestBody quoteRequest: QuoteRequest): QuoteResponse =
        quoteService.processQuote(quoteRequest)
}