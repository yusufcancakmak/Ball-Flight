package com.ballflight6463.utils

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseInjectionDialogFragment<DB:ViewDataBinding,VM:ViewModel>:BaseDialogFragment(){
    protected lateinit var binding:DB
    protected lateinit var viewModel:VM

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    protected abstract fun getViewModelClass(): Class<VM>

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = androidx.lifecycle.ViewModelProvider(this)[getViewModelClass()]
    }

    override fun onCreateView(inflater: android.view.LayoutInflater, container: android.view.ViewGroup?, savedInstanceState: android.os.Bundle?): android.view.View? {
        binding = androidx.databinding.DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        return binding.root
    }
}