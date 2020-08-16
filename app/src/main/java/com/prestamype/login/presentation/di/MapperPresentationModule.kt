package com.prestamype.login.presentation.di

import com.prestamype.login.presentation.mapper.LoginModelMapper
import com.prestamype.login.presentation.mapper.LoginModelMapperImpl
import org.koin.dsl.module

val mapperPresentationModule = module {
    single<LoginModelMapper>{ LoginModelMapperImpl() }
}