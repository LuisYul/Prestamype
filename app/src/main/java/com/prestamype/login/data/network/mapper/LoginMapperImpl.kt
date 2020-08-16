package com.prestamype.login.data.network.mapper

import com.prestamype.login.data.network.response.LoginResponse
import com.prestamype.login.domain.entity.LoginEntity

class LoginMapperImpl : LoginMapper {

    override suspend fun loginDataToDomain(login: LoginResponse): LoginEntity {
        return LoginEntity(login.data.token, login.data.userId)
    }
}