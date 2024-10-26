package com.example.basicgetirclone.ui.productList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicgetirclone.repo.CategoryRepo
import com.example.basicgetirclone.repo.CategoryRepoInterface
import com.example.basicgetirclone.repo.ProductDaoRepo
import com.example.basicgetirclone.repo.ProductDaoRepoInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private val productDaoRepo: ProductDaoRepoInterface,private val categoryRepo: CategoryRepoInterface) :
    ViewModel() {

    var categories = MutableLiveData<List<Category>>()
    var subCategory = MutableLiveData<List<SubCategory>>()
    var products = MutableLiveData<List<Product>>()
    private var selectedCategoryId: Int = 1
    private var selectedSubCategoryId:Int = 1

    fun onCreate() {
        uploadCategories()
        categoryRepo.categories.observeForever { list ->
            categories.value = list

        }

        productDaoRepo.products.observeForever { list ->
            products.value = list

        }
        uploadSubCategory(selectedCategoryId)
        fetchProduct(selectedSubCategoryId)

    }

    private fun uploadCategories() {
        viewModelScope.launch {
            categoryRepo.getAllCategories()
        }
    }

    private  fun fetchProduct(id:Int){
        viewModelScope.launch {
            productDaoRepo.getProducts(id)
        }
    }



    private fun uploadSubCategory(id: Int) {
        categoryRepo.uploadSubCategories(id)
        categoryRepo.subCategories.observeForever { list ->
            subCategory.value = list

        }
    }

    fun onClickCategory(id: Int) {
        categoryRepo.onClickCategory(id)
    }

    fun getItemCountCategoryAdapter() : Int {

        return  categoryRepo.getItemCountCategoryAdapter()
    }

    fun onBindViewHolderCategoryAdapter(position:Int) : Pair<Category,Boolean> {
        return  categoryRepo.onBindViewHolderCategoryAdapter(position)
    }

    fun getItemCountSubCategoryAdapter() :  Int {
        return categoryRepo.getItemCountSubCategoryAdapter()
    }

    fun onBindViewHolderSubCategoryAdapter(position: Int) : Pair<SubCategory,Boolean>{
        return categoryRepo.onBindViewHolderSubCategoryAdapter(position)
    }

    fun onClickSubCategory(id:Int){
        categoryRepo.onClickSubCategory(id)
        fetchProduct(id)
        productDaoRepo.products.observeForever { list ->
            products.value = list
        }
    }
}



