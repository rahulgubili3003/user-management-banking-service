package com.techie.ebanking.users.repository

import com.techie.ebanking.users.entity.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UsersRepository: JpaRepository<Users, String> {
    fun existsByName(name: String): Boolean
    fun findByName(name: String): Users?
}