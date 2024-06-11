package com.techie.ebanking.users.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.Size
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.Date

@Entity
@Table(name = "users_banking")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
data class Users(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",

    @Column(name = "username", nullable = false, unique = true)
    @Size(min = 7, max = 10, message = "Username must be at least 1 character long and should not exceed 10 characters")
    val name: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "first_name")
    val firstName: String,

    @Column(name = "last_name")
    val lastName: String,

    @Column(name = "email")
    var email: String,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: Date = Date(),

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: Date = Date(),

    @Column(name = "is_deleted", nullable = false)
    var isDeleted: Boolean = false
)
