package com.techie.ebanking.users.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder(12)
    }

    @Bean
    fun authorization(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { auth -> auth.anyRequest().permitAll() }
            .build()
    }
}