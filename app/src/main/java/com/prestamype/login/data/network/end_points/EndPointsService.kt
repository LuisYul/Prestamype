package com.prestamype.login.data.network.end_points
import com.prestamype.login.data.network.response.LoginResponse
import com.prestamype.login.domain.usecase.AuthUseCase
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface EndPointsService {

    @POST("auth/login")
    suspend fun auth(@Body params: AuthUseCase.Params) : Response<LoginResponse>
}