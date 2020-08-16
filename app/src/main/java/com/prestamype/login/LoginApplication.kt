package com.prestamype.login

import android.app.Application
import com.prestamype.login.data.di.mapperDataModule
import com.prestamype.login.data.di.networkModule
import com.prestamype.login.data.di.repositoryModule
import com.prestamype.login.domain.di.useCasesModule
import com.prestamype.login.presentation.di.mapperPresentationModule
import com.prestamype.login.presentation.di.viewModelModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LoginApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LoginApplication)
            modules(arrayListOf(networkModule, mapperDataModule, repositoryModule,
                useCasesModule, mapperPresentationModule, viewModelModule))
        }
    }
}