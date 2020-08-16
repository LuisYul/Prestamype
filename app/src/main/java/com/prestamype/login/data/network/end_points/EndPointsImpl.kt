package com.prestamype.login.data.network.end_points

import com.prestamype.login.data.network.response.LoginResponse
import com.prestamype.login.data.network.utils.ConnectionUtils
import com.prestamype.login.domain.entity.Either
import com.prestamype.login.domain.entity.Failure
import com.prestamype.login.domain.usecase.AuthUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Response
import java.net.SocketTimeoutException
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException


class EndPointsImpl(private val endPoints: EndPointsService,
                    private val networkUtils: ConnectionUtils) : EndPoints {

    companion object {
        private const val KEY_CODE = "code"
        private const val KEY_MESSAGE = "message"
    }

    override suspend fun auth(params: AuthUseCase.Params): Either<Failure, LoginResponse> = callService { endPoints.auth(params) }


    /**
     * Invoke the retrofit endpoint service in IO Context and after the response has been invoked
     * verify if its successful and if has a valid body.
     */
    private suspend inline fun <T> callService(crossinline retrofitCall: suspend () -> Response<T>): Either<Failure, T> {
        return when (networkUtils.isNetworkAvailable()) {
            true -> {
                try {
                    withContext(Dispatchers.IO) {
                        val response = retrofitCall.invoke()
                        if (response.isSuccessful && response.body() != null) {
                            return@withContext Either.Right(response.body()!!)
                        } else {
                            return@withContext Either.Left(
                                    getErrorMessageFromServer(
                                            response.errorBody()?.string()
                                    )
                            )
                        }
                    }
                } catch (e: Exception) {
                    return Either.Left(parseException(e))
                }
            }
            false -> Either.Left(Failure.NoNetworkDetected)
        }
    }

    /**
     * Parse Server Error to [Failure.ServerBodyError] if [errorBody] [isServerErrorValid].
     * @return [Failure] object.
     */
    private suspend fun getErrorMessageFromServer(errorBody: String?): Failure {
        return if (errorBody != null) {
            return withContext(Dispatchers.IO) {
                val serverErrorJson = JSONObject(errorBody)
                when {
                    isServerErrorValid(serverErrorJson.toString()) -> {
                        val code = serverErrorJson[KEY_CODE].toString().toInt()
                        if (code == 401 || code == 403) {
                            return@withContext Failure.UnauthorizedOrForbidden
                        } else {
                            return@withContext Failure.ServerBodyError(code, serverErrorJson[KEY_MESSAGE].toString())
                        }
                    }
                    serverErrorJson.toString().contains("\"$KEY_MESSAGE\"") -> {
                        return@withContext Failure.ServiceUncaughtFailure(serverErrorJson[KEY_MESSAGE].toString())
                    }
                    else -> return@withContext Failure.None
                }
            }
        } else {
            //No error body was found.
            Failure.None
        }
    }

    private fun isServerErrorValid(error: String): Boolean {
        return error.contains("\"$KEY_CODE\"") && error.contains("\"$KEY_MESSAGE\"")
    }

    private fun parseException(throwable: Throwable): Failure {
        return when (throwable) {
            is SocketTimeoutException -> Failure.TimeOut
            is SSLException -> Failure.NetworkConnectionLostSuddenly
            is SSLHandshakeException -> Failure.SSLError
            else -> Failure.ServiceUncaughtFailure(throwable.message ?: "Service response doesn't match with response object.")
        }
    }
}