package de.allianzdirect.springquoteservice.communication

data class QuoteResponse(
    var owner: Owner? = null,
    var car: Car? = null
)

data class Owner(
    var name: String? = null,
    var surname: String? = null,
    var street: String? = null,
    var number: String? = null,
    var city: String? = null
)

data class Car(
    var registrationNumber: String? = null,
    var engineType: String? = null,
    var enginePower: String? = null
)