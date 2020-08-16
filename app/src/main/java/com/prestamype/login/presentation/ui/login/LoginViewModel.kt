package com.prestamype.login.presentation.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.prestamype.login.domain.entity.LoginEntity
import com.prestamype.login.domain.usecase.AuthUseCase
import com.prestamype.login.presentation.base.BaseViewModel

class LoginViewModel(private val authUseCase: AuthUseCase) : BaseViewModel<Any>() {

    val email = MutableLiveData<String>().apply { value = "peruapps0@peruapps.com" }
    val password = MutableLiveData<String>().apply { value = "password0" }

    fun auth(){
        val params = AuthUseCase.Params(email.value ?: "", password.value ?: "")
        authUseCase.invoke(viewModelScope, params){
            it.either(::handleUseCaseFailureFromBase, ::handleUseCaseAuthSuccess)
        }
    }

    private fun handleUseCaseAuthSuccess(loginEntity: LoginEntity){
        _snackMsg.value = "Login success: token = ${loginEntity.token}"
        Log.e("login ent", Gson().toJson(loginEntity))
       //getNavigator()?.navToHome(loginResponse.data)
    }
}