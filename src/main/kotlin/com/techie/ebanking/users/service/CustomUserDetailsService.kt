package com.techie.ebanking.users.service

import com.techie.ebanking.users.repository.UsersRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
class CustomUserDetailsService(private val repository: UsersRepository): UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = repository.findByName(username)
        // Check if the user is null
        if (user!= null) {
            // If the user is not null, proceed to create and return the User object
            return User(user.name, user.password, ArrayList())
        } else {
            // If the user is null, throw an exception
            throw UsernameNotFoundException("User not found with username: $username")
        }
    }
}