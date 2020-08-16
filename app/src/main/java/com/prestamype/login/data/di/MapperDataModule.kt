package com.prestamype.login.data.di


import com.prestamype.login.data.network.mapper.LoginMapper
import com.prestamype.login.data.network.mapper.LoginMapperImpl
import org.koin.dsl.module

val mapperDataModule = module {
    single<LoginMapper>{ LoginMapperImpl() }
}
