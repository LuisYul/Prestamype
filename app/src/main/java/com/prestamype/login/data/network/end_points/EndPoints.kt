package com.prestamype.login.data.network.end_points

import com.prestamype.login.data.network.response.LoginResponse
import com.prestamype.login.domain.entity.Either
import com.prestamype.login.domain.entity.Failure
import com.prestamype.login.domain.usecase.AuthUseCase


interface EndPoints {

    suspend fun auth(params: AuthUseCase.Params) : Either<Failure, LoginResponse>

}