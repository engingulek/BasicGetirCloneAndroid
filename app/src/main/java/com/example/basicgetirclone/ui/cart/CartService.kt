package com.example.basicgetirclone.ui.cart

import com.example.basicgetirclone.retrofit.BaseDao
import com.example.basicgetirclone.retrofit.NetworkError
import com.example.basicgetirclone.retrofit.ResultData
import retrofit2.awaitResponse

interface CartServiceInterface {
    suspend fun  fetchProductFromCart(userId:Int) : ResultData<List<CartProduct>>
}


class CartService(private val bdo:BaseDao) : CartServiceInterface {
    override suspend fun fetchProductFromCart(userId: Int): ResultData<List<CartProduct>> {
        return try {
            val response = bdo.getCartByUserId(userId).awaitResponse()
            if (response.isSuccessful) {
                val cartProducts = response.body() ?: emptyList()
                ResultData.Success(cartProducts)
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