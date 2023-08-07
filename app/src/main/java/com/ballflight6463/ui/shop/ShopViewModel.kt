package com.ballflight6463.ui.shop

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class ShopViewModel @Inject constructor(): ViewModel() {
    var characterPosition: Int = 0
}