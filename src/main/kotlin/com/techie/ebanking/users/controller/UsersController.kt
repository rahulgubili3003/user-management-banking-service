package com.techie.ebanking.users.controller

import com.techie.ebanking.users.dto.request.UserLoginRequest
import com.techie.ebanking.users.dto.request.UserRegisterRequest
import com.techie.ebanking.users.dto.response.LoginResponse
import com.techie.ebanking.users.dto.response.OkResponse
import com.techie.ebanking.users.service.UsersService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UsersController(private val service: UsersService) {
    @PostMapping("/register")
    fun registerUser(@Valid @RequestBody userRegisterRequest: UserRegisterRequest): ResponseEntity<OkResponse> {
        val username = service.registerUser(userRegisterRequest)
        return ResponseEntity.ok(OkResponse(data =  username))
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody loginRequest: UserLoginRequest): ResponseEntity<LoginResponse> {
        val isLoggedIn = service.login(loginRequest)
        return when(isLoggedIn) {
            0 -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(LoginResponse("NG", "Username not found in DB"))
            1 -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(LoginResponse("NG", "Entered Password is Invalid"))
            2 -> ResponseEntity.ok(LoginResponse("OK", "Login Successful"))
            else -> ResponseEntity.internalServerError().body(LoginResponse("NG", "An unexpected error occurred"))
        }
    }

    @GetMapping("/check")
    fun check(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello")
    }
}