package com.prestamype.login.presentation.ui.login

import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.Observer
import com.prestamype.login.BR
import com.prestamype.login.R
import com.prestamype.login.databinding.ActivityLoginBinding
import com.prestamype.login.presentation.base.BaseActivity
import com.prestamype.login.presentation.extensions.alertSnack
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val getLayoutId: Int get() = R.layout.activity_login
    override val viewModel: LoginViewModel by viewModel()
    override val getBindingVariable: Int get() = BR.loginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSnack()
    }

    private fun setupSnack() {
        viewModel.snackMsg.observe(this, Observer { msg: String ->
            login_screen.alertSnack(msg)
        })
    }
}