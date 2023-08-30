package com.example.kotlin_flowexample.FlowRetrofit.api

import com.example.kotlin_flowexample.FlowRetrofit.response.ResponseCoinsList
import com.example.kotlin_flowexample.FlowRetrofit.response.ResponseDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("coins/markets?sparkline=true")
    suspend fun getCoinsList(@Query("vs_currency") vs_currency : String) : Response<ResponseCoinsList>

    @GET("coins/{id}?sparkline=true")
    suspend fun getDetailsCoin(@Path("id") id : String) : Response<ResponseDetails>

}


