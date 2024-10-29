package com.example.basicgetirclone.ui.cart

import com.example.basicgetirclone.retrofit.BaseDao
import com.example.basicgetirclone.retrofit.NetworkError
import com.example.basicgetirclone.retrofit.ResultData
import retrofit2.awaitResponse

interface CartServiceInterface {
    suspend fun  fetchProductFromCart(userId:Int) : ResultData<List<CartProduct>>
    suspend fun addProductToCart(product:ProductRequest) : Boolean
    suspend fun decreaseProductFromCart(cartId:Int) : Boolean
    suspend fun increaseProduct(cartId: Int) : Boolean
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

    override suspend fun addProductToCart(product: ProductRequest) : Boolean {
        return try {
            val response = bdo.addProduct(product).awaitResponse()
            response.isSuccessful
        }catch (e :Exception){
            true
        }
    }

    override suspend fun decreaseProductFromCart(cartId: Int) : Boolean {
        return try {
            val response = bdo.decreaseProductFromCart(cartId).awaitResponse()
            response.isSuccessful
        }catch (e :Exception){
            true
        }
    }

    override suspend fun increaseProduct(cartId: Int): Boolean {
        return try {
            val response = bdo.incrementProduct(cartId).awaitResponse()
            response.isSuccessful
        }catch (e :Exception){
            true
        }
    }
}