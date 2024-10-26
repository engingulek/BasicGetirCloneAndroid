package com.example.basicgetirclone.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.basicgetirclone.retrofit.CategoryDao
import com.example.basicgetirclone.ui.productList.Category
import com.example.basicgetirclone.ui.productList.Product
import com.example.basicgetirclone.ui.productList.SubCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface ProductDaoRepoInterface {
    var categories: MutableLiveData<List<Category>>
    var subCategories: MutableLiveData<List<SubCategory>>
    var products:MutableLiveData<List<Product>>

    fun getAllCategories()
    fun uploadSubCategories(id: Int)
    fun getProducts(id:Int)
}

class ProductDaoRepo(var cdo: CategoryDao) : ProductDaoRepoInterface {
    override var categories: MutableLiveData<List<Category>> = MutableLiveData()
    override var subCategories: MutableLiveData<List<SubCategory>> = MutableLiveData()
    override var products:MutableLiveData<List<Product>> = MutableLiveData()

    override fun getAllCategories() {
        cdo.allCategoriesGet().enqueue(object : Callback<List<Category>> {
            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<List<Category>>,
                response: Response<List<Category>>
            ) {
                val list = response.body()
                categories.value = list ?: emptyList()
            }
        })
    }

    override fun uploadSubCategories(id: Int) {
        categories.observeForever { list ->
            val subCategoryFilter = list.first { it.id == id }.subCategory

            subCategories.value = subCategoryFilter
        }
    }

    override fun getProducts(id: Int) {
        cdo.getProductBySubCategoryId(id).enqueue(object :Callback<List<Product>>{
            override fun onResponse(call: Call<List<Product>>,
                                    response: Response<List<Product>>) {
                val list = response.body()
                products.value = list ?: emptyList()
                Log.e("Repo List","$id")
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {

            }

        })
    }
}