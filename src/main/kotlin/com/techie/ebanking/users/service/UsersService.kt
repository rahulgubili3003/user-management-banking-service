package com.techie.ebanking.users.service

import com.techie.ebanking.users.dto.request.UserLoginRequest
import com.techie.ebanking.users.dto.request.UserRegisterRequest
import com.techie.ebanking.users.exception.DuplicateUsernameException
import com.techie.ebanking.users.repository.UsersRepository
import com.techie.ebanking.users.util.jwt.JwtUtil
import com.techie.ebanking.users.util.mappers.DtoToEntityMapper
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import lombok.extern.slf4j.Slf4j
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
@Slf4j
class UsersService(
    private val usersRepository: UsersRepository,
    private val encoder: BCryptPasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil
) {

    @Transactional
    fun registerUser(@Valid userRegisterRequest: UserRegisterRequest): String {
        val name = userRegisterRequest.username
        val isExists = usersRepository.existsByName(name)
        if (isExists) {
            throw DuplicateUsernameException(message = "Username already taken: $name")
        }
        val userEntity = DtoToEntityMapper.mapDtoToEntity(userRegisterRequest)
        val encryptedPass = encoder.encode(userEntity.password)
        userEntity.password = encryptedPass
        val savedEntity = usersRepository.save(userEntity)
        return savedEntity.name
    }

    fun login(@Valid userLoginRequest: UserLoginRequest): String {
        val username = userLoginRequest.username
        val password = userLoginRequest.password
        val token = authenticate(username, password)
        return token
    }

    private fun authenticate(username: String, password: String): String {
        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    username,
                    password
                )
            )
        } catch (e: AuthenticationException) {
            throw RuntimeException("Authentication Failed")
        }
        return jwtUtil.generateToken(username)
    }
}