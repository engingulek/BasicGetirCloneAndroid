package com.example.basicgetirclone.ui.productList

import com.example.basicgetirclone.retrofit.CategoryDao
import com.example.basicgetirclone.retrofit.NetworkError
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse

sealed class ResultData<out T> {
    data class Success<out T>(val data: T) : ResultData<T>()
    data class Error(val error: NetworkError) : ResultData<Nothing>()
}

interface ProductListPageServiceInterface {
    suspend fun fetchCategories(): ResultData<List<Category>>
    suspend fun fetchProducts(id:Int) : ResultData<List<Product>>
}

class ProductListPageService(private val cdo: CategoryDao) : ProductListPageServiceInterface {
    override suspend fun fetchCategories(): ResultData<List<Category>> {
        return try {
            val response = cdo.allCategoriesGet().awaitResponse()
            if (response.isSuccessful) {
                val categories = response.body() ?: emptyList()
                ResultData.Success(categories)
            } else {
                ResultData.Error(when (response.code()) {
                    400 -> NetworkError.BadRequest
                    404 -> NetworkError.NotFound
                    else -> NetworkError.InvalidResponse
                })
            }
        } catch (e: Exception) {
            ResultData.Error(NetworkError.UnexpectedError)
        }
    }

    override suspend fun fetchProducts(id:Int): ResultData<List<Product>> {
        return try {
            val response = cdo.getProductBySubCategoryId(id).awaitResponse()
            if (response.isSuccessful) {
                val categories = response.body() ?: emptyList()
                ResultData.Success(categories)
            } else {
                ResultData.Error(when (response.code()) {
                    400 -> NetworkError.BadRequest
                    404 -> NetworkError.NotFound
                    else -> NetworkError.InvalidResponse
                })
            }
        } catch (e: Exception) {
            ResultData.Error(NetworkError.UnexpectedError)
        }
    }
}

