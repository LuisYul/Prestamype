package com.prestamype.login.presentation.mapper

import com.prestamype.login.domain.entity.LoginEntity
import com.prestamype.login.presentation.model.LoginModel

class LoginModelMapperImpl : LoginModelMapper {

    override suspend fun loginDomainToPresentation(login: LoginEntity): LoginModel {
        return LoginModel(token = login.token, userId = login.userId)
    }
}