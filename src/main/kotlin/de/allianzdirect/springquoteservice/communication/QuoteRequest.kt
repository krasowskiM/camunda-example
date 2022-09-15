package de.allianzdirect.springquoteservice.communication

data class QuoteRequest(
    var name: String? = null,
    var surname: String? = null,
    var registrationNumber: String? = null,
    var engineType: String? = null
)