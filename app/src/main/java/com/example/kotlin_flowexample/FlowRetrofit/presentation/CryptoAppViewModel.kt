package com.example.kotlin_flowexample.FlowRetrofit.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_flowexample.FlowRetrofit.repository.ApiRepository
import com.example.kotlin_flowexample.FlowRetrofit.response.ResponseCoinsList
import com.example.kotlin_flowexample.FlowRetrofit.response.ResponseDetails
import com.example.kotlin_flowexample.FlowRetrofit.utils.DataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoAppViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

    //List of Coins
    private val _coinsList = MutableLiveData<DataStatus<List<ResponseCoinsList.ResponseCoinsListItem>>>()
    val coinsList: LiveData<DataStatus<List<ResponseCoinsList.ResponseCoinsListItem>>>
        get() = _coinsList


    fun getCoinsList(vs_currency: String) = viewModelScope.launch {
        repository.getCoinsList(vs_currency).collect{
            _coinsList.value =it
        }
    }


    // Details of Coins
    private val _detailsCoin = MutableLiveData<DataStatus<ResponseDetails>>()
    val detailsCoin: LiveData<DataStatus<ResponseDetails>>
        get() = _detailsCoin

    fun getDetailsCoin(id: String) = viewModelScope.launch {
        repository.getDetailsCoin(id).collect{
            _detailsCoin.value=it
        }
    }



}