package com.example.basicgetirclone.ui.productList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.basicgetirclone.repo.ProductDaoRepo
import com.example.basicgetirclone.repo.ProductDaoRepoInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private val productDaoRepo: ProductDaoRepoInterface) :
    ViewModel() {

    var categories = MutableLiveData<List<Category>>()
    var subCategory = MutableLiveData<List<SubCategory>>()
    private var selectedCategoryId: Int = 1

    fun onCreate() {
        uploadCategories()
        productDaoRepo.categories.observeForever { list ->
            categories.value = list

        }
        uploadSubCategory(selectedCategoryId)
    }

    private fun fetchCategories() {
        productDaoRepo.getAllCategories()

    }

    private fun uploadCategories() {
        fetchCategories()
    }

    private fun uploadSubCategory(id: Int) {
        productDaoRepo.uploadSubCategories(id)
        productDaoRepo.subCategories.observeForever { list ->
            subCategory.value = list
        }
    }

    private  fun getCategoriesWithoutNullAble() : List<Category> {
        val categoryList = categories.value ?: emptyList()
        return  categoryList
    }

    fun onClickCategory(id: Int) {
        selectedCategoryId = id
        uploadSubCategory(selectedCategoryId)

    }

    fun getItemCount() : Int {
        val categories =  getCategoriesWithoutNullAble()
        return  categories.size
    }

    fun onBindViewHolder(position:Int) : Pair<Category,Boolean> {
        val categoryList =  getCategoriesWithoutNullAble()
        val  category:Category = categoryList[position]
        val visibleState:Boolean = category.id == selectedCategoryId
        return  Pair(category,visibleState)
    }
}



