package com.example.basicgetirclone.ui.productList.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Category(@SerializedName("id") var id:Int,
                    @SerializedName("name") var name:String,
                    @SerializedName("subCategories") var subCategory:List<SubCategory>) : Serializable {
}

data class SubCategory (
    @SerializedName("id") val id: Int,
    @SerializedName("name")val name: String
) : Serializable {}
