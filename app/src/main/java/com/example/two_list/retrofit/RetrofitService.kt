package com.example.two_list.retrofit

import com.example.two_list.model.Wallets
import com.example.two_list.model.WalletsItem
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("wallets")
    fun getWalletList(): Call<MutableList<WalletsItem>>
}