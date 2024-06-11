package com.techie.ebanking.users.util.mappers

import com.techie.ebanking.users.dto.request.UserRegisterRequest
import com.techie.ebanking.users.entity.Users

class DtoToEntityMapper {
    companion object {
        fun mapDtoToEntity(userRegisterRequest: UserRegisterRequest): Users {
            return Users(
                name = userRegisterRequest.username,
                password = userRegisterRequest.password,
                firstName = userRegisterRequest.firstName,
                lastName = userRegisterRequest.lastName,
                email = userRegisterRequest.email
            )
        }
    }
}