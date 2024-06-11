package com.techie.ebanking.users.dto.request

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import jakarta.validation.constraints.Size

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserRegisterRequest(
    @field:Size(min = 7, max = 20, message = "The length should within the range of 7 and 20")
    val username: String,
    @field:Size(min = 7, max = 20, message = "The length should within the range of 7 and 20")
    val password: String,
    val email: String,
    val firstName: String,
    val lastName: String
)
