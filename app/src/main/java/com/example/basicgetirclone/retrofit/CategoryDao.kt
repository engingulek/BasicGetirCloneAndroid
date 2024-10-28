package com.example.basicgetirclone.retrofit

import com.example.basicgetirclone.ui.cart.CartProduct
import com.example.basicgetirclone.ui.productList.models.Category
import com.example.basicgetirclone.ui.productList.models.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseDao {
    @GET("category/getAll")
    fun allCategoriesGet():Call<List<Category>>

    @GET("product/bySubCategoryId")
    fun getProductBySubCategoryId(
        @Query("subCategoryId") id: Int
    ): Call<List<Product>>


    @GET("cart/getCartByUserId")
    fun getCartByUserId(
        @Query("userId") userId:Int
    ):Call<List<CartProduct>>

}