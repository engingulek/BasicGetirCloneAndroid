package com.example.basicgetirclone.ui.cart

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class CartProduct (
    @SerializedName("id")  val id: Int,
    @SerializedName("imageurl")  val imageURL: String,
    @SerializedName("name")  val name: String,
    @SerializedName("about_product")   val aboutProduct: String,
    @SerializedName("price")  val price: Double,
    @SerializedName("quantity")  val quantity: Int,
    @SerializedName("product_id")   val productID: Int
) : Serializable