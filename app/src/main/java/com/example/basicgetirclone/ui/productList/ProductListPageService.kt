package com.example.basicgetirclone.ui.productList

import com.example.basicgetirclone.retrofit.BaseDao
import com.example.basicgetirclone.retrofit.NetworkError
import com.example.basicgetirclone.retrofit.ResultData
import com.example.basicgetirclone.ui.cart.ProductRequest
import com.example.basicgetirclone.ui.productList.models.Category
import com.example.basicgetirclone.ui.productList.models.Product
import retrofit2.awaitResponse



interface ProductListPageServiceInterface {
    suspend fun fetchCategories(): ResultData<List<Category>>
    suspend fun fetchProducts(id:Int) : ResultData<List<Product>>
}

class ProductListPageService(private val bdo: BaseDao) : ProductListPageServiceInterface {
    override suspend fun fetchCategories(): ResultData<List<Category>> {
        return try {
            val response = bdo.allCategoriesGet().awaitResponse()
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
            val response = bdo.getProductBySubCategoryId(id).awaitResponse()
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

