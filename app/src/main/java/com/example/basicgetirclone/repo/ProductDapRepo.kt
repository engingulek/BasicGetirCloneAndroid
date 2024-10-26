package com.example.basicgetirclone.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.basicgetirclone.retrofit.CategoryDao
import com.example.basicgetirclone.ui.productList.Category
import com.example.basicgetirclone.ui.productList.Product
import com.example.basicgetirclone.ui.productList.ProductListPageServiceInterface
import com.example.basicgetirclone.ui.productList.ResultData
import com.example.basicgetirclone.ui.productList.SubCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

interface ProductDaoRepoInterface {
    var categories: MutableLiveData<List<Category>>
    var subCategories: MutableLiveData<List<SubCategory>>
    var products:MutableLiveData<List<Product>>

    suspend  fun getAllCategories()
    fun uploadSubCategories(id: Int)
   suspend fun getProducts(id:Int)
}

class ProductDaoRepo (private var productListService: ProductListPageServiceInterface) : ProductDaoRepoInterface {
    override var categories: MutableLiveData<List<Category>> = MutableLiveData()
    override var subCategories: MutableLiveData<List<SubCategory>> = MutableLiveData()
    override var products:MutableLiveData<List<Product>> = MutableLiveData()

    override suspend fun getAllCategories() {
        when(val result = productListService.fetchCategories()){
            is ResultData.Success -> {
                val list = result.data
                categories.value = list
            }

            is ResultData.Error -> {
                Log.e("ResultDataError","${result.error.toString()}")
            }
        }
    }

    override fun uploadSubCategories(id: Int) {
        categories.observeForever { list ->
            val subCategoryFilter = list.first { it.id == id }.subCategory

            subCategories.value = subCategoryFilter
        }
    }

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
}