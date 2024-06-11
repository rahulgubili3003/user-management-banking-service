package com.techie.ebanking.users.dto.response

data class FormattedErrorResponse(
    val errorCode: String,
    val errorMessage: String,
    val cause: String
)