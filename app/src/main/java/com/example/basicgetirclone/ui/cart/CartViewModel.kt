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

    fun onCreate() {
        fetchCartProduct(1)
        cartRepo.cartProducts.observeForever { list ->
            cartProducts.value = list
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
}