package com.example.basicgetirclone.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.basicgetirclone.ui.productList.models.Product
import com.example.basicgetirclone.ui.productList.ProductListPageServiceInterface
import com.example.basicgetirclone.ui.productList.ResultData

interface ProductRepoInterface {
    var products:MutableLiveData<List<Product>>
    suspend fun getProducts(id:Int)
    fun getItemCount(): Int
    fun onBindViewHolder(position:Int) : Product
}

class ProductDaoRepo (private var productListService: ProductListPageServiceInterface) : ProductRepoInterface {

    override var products:MutableLiveData<List<Product>> = MutableLiveData()

    override suspend fun getProducts(id: Int) {
        when(val result = productListService.fetchProducts(id)){
            is ResultData.Success -> {
                val list = result.data
                products.value = list
            }

            is ResultData.Error -> {
                Log.e("ResultDataError","${result.error.toString()}")
            }
        }
    }

  private  fun getItemProductWithoutNullAble() : List<Product> {
      val list = products.value ?: emptyList()
        return  list
    }
    override fun getItemCount(): Int {
        val size = getItemProductWithoutNullAble().size
        return  size
    }

    override fun onBindViewHolder(position: Int) : Product {
        val list = getItemProductWithoutNullAble()
        val product: Product = list[position]
        return product
    }
}