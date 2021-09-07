package com.example.two_list.common

import com.example.two_list.retrofit.RetrofitClient
import com.example.two_list.retrofit.RetrofitServices

object Common {
    private val BASE_URL = "https://www.amock.io/api/Belerafo/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}