package com.example.basicgetirclone.ui.cart


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicgetirclone.repo.CartRepoInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepo: CartRepoInterface )
    : ViewModel() {
        var cartProducts = MutableLiveData<List<CartProduct>>()
        var total = MutableLiveData<Double>(0.0)

    fun onCreate() {
        fetchCartProduct(1)
        cartRepo.cartProducts.observeForever { list ->
            cartProducts.value = list
        }
        cartRepo.totalAmount.observeForever {
            total.value = it
        }
    }

    private fun fetchCartProduct(userId:Int){
        viewModelScope.launch {
            cartRepo.getCartProducts(userId)
        }
    }


    fun getItemCount() : Int {
        return  cartRepo.getItemCount()
    }

    fun onBindViewHolder(position:Int): CartProduct{
        return cartRepo.onBindViewHolder(position)
    }

    fun decreamentOnCLick(cartId:Int){
        viewModelScope.launch {
            cartRepo.decreaseProductFromCart(cartId)
        }
    }

    fun increamentOnClick(cartId:Int){
        viewModelScope.launch {
            cartRepo.incrementProduct(cartId)
        }
    }
}