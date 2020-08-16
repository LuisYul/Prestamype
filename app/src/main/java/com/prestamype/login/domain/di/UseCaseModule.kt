package com.prestamype.login.domain.di

import com.prestamype.login.domain.usecase.AuthUseCase
import org.koin.dsl.module


val useCasesModule = module {
    factory { AuthUseCase(get()) }
}