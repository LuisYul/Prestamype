package com.prestamype.login.domain.repository

import com.prestamype.login.domain.entity.Either
import com.prestamype.login.domain.entity.Failure
import com.prestamype.login.domain.entity.LoginEntity
import com.prestamype.login.domain.usecase.AuthUseCase

interface LoginRepository {

    suspend fun auth(params: AuthUseCase.Params) : Either<Failure, LoginEntity>

}