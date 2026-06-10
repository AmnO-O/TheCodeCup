package com.example.thecodecup.data.model

import androidx.annotation.DrawableRes

data class Coffee(
    val id: String,
    val name: String,
    val description: String = "",
    val price: Double,
    @DrawableRes val imageRes: Int
)
