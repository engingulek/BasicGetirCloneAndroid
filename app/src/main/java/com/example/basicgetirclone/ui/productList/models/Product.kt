package com.example.basicgetirclone.ui.productList.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product (
    @SerializedName("id")  val id: Int,
    @SerializedName("imageUrl")  val imageURL: String,
    @SerializedName("name")  val name: String,
    @SerializedName("price")  val price: Double,
    @SerializedName("aboutProduct")   val aboutProduct: String,
    var piece:Int? = 0

) : Serializable