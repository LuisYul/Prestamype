package com.prestamype.login.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding, out V : BaseViewModel<*>> : AppCompatActivity() {

    private lateinit var viewDataBinding: T
    abstract val getLayoutId: Int
    abstract val viewModel : V
    abstract val getBindingVariable: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId)
        viewDataBinding.setVariable(getBindingVariable, viewModel)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.executePendingBindings()
    }
}