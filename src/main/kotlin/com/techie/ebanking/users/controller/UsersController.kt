package com.techie.ebanking.users.controller

import com.techie.ebanking.users.dto.request.UserLoginRequest
import com.techie.ebanking.users.dto.request.UserRegisterRequest
import com.techie.ebanking.users.dto.response.LoginResponse
import com.techie.ebanking.users.dto.response.OkResponse
import com.techie.ebanking.users.service.UsersService
import jakarta.validation.Valid
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
        val token = service.login(loginRequest)
        return ResponseEntity.ok(LoginResponse("OK", "Login Successful", token))
    }

    @GetMapping("/check")
    fun check(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello")
    }
}