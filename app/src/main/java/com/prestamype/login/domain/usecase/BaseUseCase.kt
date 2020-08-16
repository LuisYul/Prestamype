package com.prestamype.login.domain.usecase

import com.prestamype.login.domain.entity.Either
import com.prestamype.login.domain.entity.Failure
import kotlinx.coroutines.*


abstract class BaseUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    open operator fun invoke(scope: CoroutineScope, params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        val backgroundJob = scope.async(Dispatchers.IO) { run(params) }
        scope.launch { onResult(backgroundJob.await()) }
    }
}