package com.prestamype.login.data.di

import com.prestamype.login.data.repository.LoginRepositoryImpl
import com.prestamype.login.domain.repository.LoginRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<LoginRepository> { LoginRepositoryImpl(get(), get()) }
}