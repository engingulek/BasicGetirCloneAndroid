package com.example.basicgetirclone.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.basicgetirclone.retrofit.ResultData
import com.example.basicgetirclone.ui.cart.CartProduct
import com.example.basicgetirclone.ui.cart.CartServiceInterface
import kotlin.math.sign

interface  CartRepoInterface {
    var cartProducts:MutableLiveData<List<CartProduct>>
    suspend fun getCartProducts(userId:Int)
    fun getItemCount() : Int
    fun onBindViewHolder(position:Int) : CartProduct
}


class CartRepo(private  var service:CartServiceInterface) : CartRepoInterface{
  override  var cartProducts:MutableLiveData<List<CartProduct>> = MutableLiveData()
    override suspend fun getCartProducts(userId:Int) {
        when(val result = service.fetchProductFromCart(userId)){
            is ResultData.Success -> {
                val list = result.data
                cartProducts.value = list
            }

            is  ResultData.Error -> {
                Log.e("ResultDataError CartProduct","${result.error}")
            }
        }
    }

    private  fun cartProductsValueWithoutNullAble(): List<CartProduct>{
        val list = cartProducts.value ?: emptyList()
        return  list
    }

    override fun getItemCount(): Int {
        val size = cartProductsValueWithoutNullAble().size
        return  size
    }

    override fun onBindViewHolder(position: Int): CartProduct {
        val list  = cartProductsValueWithoutNullAble()
        val cartProduct = list[position]
        return  cartProduct
    }

}