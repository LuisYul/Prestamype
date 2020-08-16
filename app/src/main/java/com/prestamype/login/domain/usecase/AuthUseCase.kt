package com.prestamype.login.domain.usecase
import com.prestamype.login.domain.entity.LoginEntity
import com.prestamype.login.domain.repository.LoginRepository


class AuthUseCase(private val loginRepository: LoginRepository) : BaseUseCase<LoginEntity, AuthUseCase.Params>() {

    override suspend fun run(params: Params) = loginRepository.auth(params)

    data class Params(val email: String, val password: String)
}