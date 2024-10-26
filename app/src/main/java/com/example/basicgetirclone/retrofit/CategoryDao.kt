package com.example.basicgetirclone.retrofit

import com.example.basicgetirclone.ui.productList.Category
import com.example.basicgetirclone.ui.productList.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CategoryDao {
    @GET("category/getAll")
    fun allCategoriesGet():Call<List<Category>>

    @GET("product/bySubCategoryId")
    fun getProductBySubCategoryId(
        @Query("subCategoryId") id: Int
    ): Call<List<Product>>


}