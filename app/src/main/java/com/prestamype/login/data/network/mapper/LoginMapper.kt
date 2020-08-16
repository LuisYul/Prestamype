package com.prestamype.login.data.network.mapper

import com.prestamype.login.data.network.response.LoginResponse
import com.prestamype.login.domain.entity.LoginEntity


interface LoginMapper {

    suspend fun loginDataToDomain(login: LoginResponse) : LoginEntity

}