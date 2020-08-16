package com.prestamype.login.presentation.mapper
import com.prestamype.login.domain.entity.LoginEntity
import com.prestamype.login.presentation.model.LoginModel

interface LoginModelMapper {

    suspend fun loginDomainToPresentation(login: LoginEntity) : LoginModel

}