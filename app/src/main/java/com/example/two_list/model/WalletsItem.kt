package com.example.two_list.model

import com.google.gson.annotations.SerializedName

data class WalletsItem(
    @SerializedName("balance")
    val balance: String,
    @SerializedName("wallet_name")
    val wallet_name: String
)