package com.techie.ebanking.users.dto.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class LoginResponse(
    val result: String,
    val status: String,
    val authToken: String
)
