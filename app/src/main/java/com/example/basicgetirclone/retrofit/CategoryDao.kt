package com.example.basicgetirclone.retrofit

import com.example.basicgetirclone.ui.productList.Category
import retrofit2.Call
import retrofit2.http.GET

interface CategoryDao {
    @GET("category/getAll")
    fun allCategoriesGet():Call<List<Category>>


}