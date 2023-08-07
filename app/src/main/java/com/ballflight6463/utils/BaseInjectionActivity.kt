package com.ballflight6463.utils

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseInjectionActivity <DB:ViewDataBinding, VM:ViewModel>:BaseActivity(){
    protected lateinit var binding:DB
    protected lateinit var viewModel:VM
    @LayoutRes
    protected abstract fun getLayoutRes():Int

    protected abstract fun getViewModelClass():Class<VM>

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        binding = androidx.databinding.DataBindingUtil.setContentView(this,getLayoutRes())
        viewModel = androidx.lifecycle.ViewModelProvider(this)[getViewModelClass()]
    }
}