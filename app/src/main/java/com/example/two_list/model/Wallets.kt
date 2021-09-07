package com.example.two_list.model

import com.google.gson.annotations.SerializedName

data class Wallets(
 @SerializedName("results")
 val results: List<WalletsItem>
)