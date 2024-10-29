package com.example.basicgetirclone.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.basicgetirclone.retrofit.ResultData
import com.example.basicgetirclone.ui.cart.CartProduct
import com.example.basicgetirclone.ui.cart.CartServiceInterface
import com.example.basicgetirclone.ui.cart.ProductRequest
import kotlin.math.sign

interface  CartRepoInterface {
    var cartProducts:MutableLiveData<List<CartProduct>>
    var totalAmount:MutableLiveData<Double>
    suspend fun getCartProducts(userId:Int)
    fun getItemCount() : Int
    fun onBindViewHolder(position:Int) : CartProduct
    suspend fun addProductToCart(product:ProductRequest)
    suspend fun decreaseProductFromCart(cartId:Int)
    suspend fun incrementProduct(cartId: Int)

}


class CartRepo(private  var service:CartServiceInterface) : CartRepoInterface{
  override  var cartProducts:MutableLiveData<List<CartProduct>> = MutableLiveData()
    override var totalAmount:MutableLiveData<Double> = MutableLiveData(0.0)
    override suspend fun getCartProducts(userId:Int) {
        when(val result = service.fetchProductFromCart(userId)){
            is ResultData.Success -> {
                val list = result.data
                cartProducts.value = list
                val total = list.sumOf { it.quantity * it.price }
                totalAmount.value = total

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

    override suspend fun addProductToCart(product: ProductRequest) {
        val result = service.addProductToCart(product)
        if (!result){
            Log.e("Add product added","d")
        }else{
            Log.e("Add product error","aaaaa")
        }
    }

    override suspend fun decreaseProductFromCart(cartId: Int) {
        val result = service.decreaseProductFromCart(cartId)
        if (!result){
            Log.e("decrease product ","d")
        }else{
            Log.e("decrease product error","aaaaa")
        }
    }

    override suspend fun incrementProduct(cartId: Int) {
        val result = service.increaseProduct(cartId)
        if (!result){
            Log.e("decrease product ","d")
        }else{
            Log.e("decrease product error","aaaaa")
        }
    }



}