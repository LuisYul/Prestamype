package com.prestamype.login.presentation.di


import com.prestamype.login.presentation.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { LoginViewModel( get()) }
}