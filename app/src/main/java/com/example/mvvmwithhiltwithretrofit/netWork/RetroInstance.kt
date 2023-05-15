package com.example.mvvmwithhiltwithretrofit.netWork

import com.example.mvvmwithhiltwithretrofit.netWork.dataClass.api
import retrofit2.Call
import retrofit2.http.GET

interface RetroInstance {

    @GET("v3/2f06b453-8375-43cf-861a-06e95a951328")
    fun getDataFromAPI(): Call<api>
}