package com.prestamype.login.data.repository

import com.prestamype.login.data.network.end_points.EndPoints
import com.prestamype.login.data.network.mapper.LoginMapper
import com.prestamype.login.domain.entity.Either
import com.prestamype.login.domain.entity.Failure
import com.prestamype.login.domain.entity.LoginEntity
import com.prestamype.login.domain.repository.LoginRepository
import com.prestamype.login.domain.usecase.AuthUseCase

class LoginRepositoryImpl(private val endPoints: EndPoints,
                          private val mapper: LoginMapper) : LoginRepository {

    override suspend fun auth(params: AuthUseCase.Params): Either<Failure, LoginEntity> {
        return when(val response = endPoints.auth(params)){
            is Either.Right -> Either.Right(mapper.loginDataToDomain(response.b))
            is Either.Left -> Either.Left(response.a)
        }
    }
}