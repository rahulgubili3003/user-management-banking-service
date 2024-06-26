package com.techie.ebanking.users.exception

import com.techie.ebanking.users.constants.ErrorConstantCodes.ERROR_001
import com.techie.ebanking.users.constants.ErrorConstantCodes.ERROR_002
import com.techie.ebanking.users.constants.ErrorMessageConstants.USERNAME_ALREADY_EXISTS
import com.techie.ebanking.users.constants.ErrorMessageConstants.USERNAME_OR_PASSWORD_INVALID
import com.techie.ebanking.users.dto.response.FormattedErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateUsernameException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleDuplicateUsername(ex: DuplicateUsernameException): ResponseEntity<FormattedErrorResponse> {
        val errorResponse = FormattedErrorResponse(
            errorCode = ERROR_001,
            errorMessage = USERNAME_ALREADY_EXISTS,
            cause = ex.message?: "Username is duplicated."
        )
        return ResponseEntity(errorResponse, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(UsernameOrPasswordInvalidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleAuthFailureException(ex: UsernameOrPasswordInvalidException): ResponseEntity<FormattedErrorResponse> {
        val errorResponse = ex.message?.let {
            FormattedErrorResponse(
                errorCode = ERROR_002,
                errorMessage = USERNAME_OR_PASSWORD_INVALID,
                cause = it
            )
        }
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}