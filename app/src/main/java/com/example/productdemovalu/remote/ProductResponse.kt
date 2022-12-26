package com.example.productdemovalu.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val category: String?,
    val description: String?,
    val id: Int?,
    val image: String?,
    val price: Double?,
    val rating: Rating?,
    val title: String?
) : Parcelable

data class Rating(
    val count: Int?,
    val rate: Double?
)