package com.example.composeclub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class MainViewModel : ViewModel() {

    val queryPricingModel: StateFlow<PricingModel> =
        flow {
            kotlinx.coroutines.delay(1_000)
            emit(
                ButtonModel(
                    priceYouPay = "$4.99/month",
                    wouldHavePaid = "$16.99",
                    savings = "Save 16%"
                )
            )
            kotlinx.coroutines.delay(1_000)
            emit(
                ButtonModel(
                    priceYouPay = "$7.99/month",
                    wouldHavePaid = "$24.99",
                )
            )
        }.stateIn(viewModelScope, SharingStarted.Lazily ,Offline)
    }
