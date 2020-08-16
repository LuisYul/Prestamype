package com.prestamype.login.presentation.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prestamype.login.domain.entity.Failure
import com.prestamype.login.presentation.utils.SingleLiveEvent
import java.lang.ref.WeakReference

abstract class BaseViewModel<T> : ViewModel() {

    protected val _snackMsg = SingleLiveEvent<String>()
    val snackMsg: LiveData<String> = _snackMsg
    private var navigator: WeakReference<T>? = null
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    protected fun logError(errorMessage: String?) {
        Log.e(this.javaClass.simpleName, errorMessage ?: "error message is null.")
    }

    protected fun logInfo(infoMessage: String?) {
        Log.i(this.javaClass.simpleName, infoMessage ?: "info message is null.")
    }

    fun getNavigator(): T? {
        return navigator?.get()
    }

    fun setNavigator(navigator: T) {
        this.navigator = WeakReference(navigator)
    }

    protected fun showLoading(loadingValue: Boolean) {
        _isLoading.value = loadingValue
    }

    protected fun handleUseCaseFailureFromBase(failure: Failure) {
        when (failure) {
            is Failure.UnauthorizedOrForbidden -> logError("Log Out") /* Log out of the app*/
            is Failure.None -> setError("None"/*R.string.snack_bar_error_failure_none*/)
            is Failure.NetworkConnectionLostSuddenly -> setError("Connection lost suddenly. Check the wifi or mobile data."/*R.string.snack_bar_error_failure_network_connection_lost_suddenly*/)
            is Failure.NoNetworkDetected -> setError("No network detected"/*R.string.snack_bar_error_failure_no_network_detected*/)
            is Failure.SSLError -> setError("WARNING: SSL Exception"/*R.string.snack_bar_error_failure_ssl*/)
            is Failure.TimeOut -> setError("Time out."/*R.string.snack_bar_error_failure_time_out*/)
            is Failure.ServerBodyError -> setError(failure.message)
            is Failure.DataToDomainMapperFailure -> setError("Data to domain mapper failure: ${failure.mapperException}"/*failure.mapperException?:R.string.snack_bar_error_general*/)
            is Failure.ServiceUncaughtFailure -> setError(failure.uncaughtFailureMessage)
        }
        showLoading(false)
    }

    protected fun setError(cause: Any) {
        if (cause is String) {
            logError(cause)
            _snackMsg.value = cause
        }
    }

    override fun onCleared() {
        navigator?.clear()
        super.onCleared()
    }
}
