package com.techie.ebanking.users.dto.request

import jakarta.validation.constraints.Size

data class UserLoginRequest(
    @field:Size(min = 7, max = 20, message = "The length should within the range of 7 and 20")
    val username: String,
    @field:Size(min = 7, max = 20, message = "The length should within the range of 7 and 20")
    val password: String
)
