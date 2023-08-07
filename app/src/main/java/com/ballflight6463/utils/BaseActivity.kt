package com.ballflight6463.utils

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity:LocalizationActivity() {
    private val inputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    fun hideSoftKeyboard() {
        currentFocus?.let {
            inputMethodManager.hideSoftInputFromWindow(it.windowToken,0)
        }
    }

}