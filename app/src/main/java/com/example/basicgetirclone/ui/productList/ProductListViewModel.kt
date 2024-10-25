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
    private var selectedSubCategoryId:Int = 1

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
            selectedSubCategoryId = list.first().id
        }
    }

    private  fun getCategoriesWithoutNullAble() : List<Category> {
        val categoryList = categories.value ?: emptyList()
        return  categoryList
    }

    private  fun getSubCategoryWithoutNullAble() : List<SubCategory>{
        val list = subCategory.value ?: emptyList()
        return  list
    }

    fun onClickCategory(id: Int) {
        selectedCategoryId = id

        uploadSubCategory(selectedCategoryId)
    }

    fun getItemCountCategoryAdapter() : Int {
        val categories =  getCategoriesWithoutNullAble()
        return  categories.size
    }

    fun onBindViewHolderCategoryAdapter(position:Int) : Pair<Category,Boolean> {
        val categoryList =  getCategoriesWithoutNullAble()
        val  category:Category = categoryList[position]
        val visibleState:Boolean = category.id == selectedCategoryId
        return  Pair(category,visibleState)
    }

    fun getItemCountSubCategoryAdapter() :  Int {
        val list = getSubCategoryWithoutNullAble()
        return list.count()
    }

    fun onBindViewHolderSubCategoryAdapter(position: Int) : Pair<SubCategory,Boolean>{
        val list = getSubCategoryWithoutNullAble()
        val subCategory:SubCategory = list[position]
        val visibleState = subCategory.id == selectedSubCategoryId
        return Pair(subCategory,visibleState)
    }

    fun onClickSubCategory(id:Int){
        selectedSubCategoryId = id
    }
}



