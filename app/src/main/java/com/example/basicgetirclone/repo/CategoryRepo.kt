package com.example.basicgetirclone.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.basicgetirclone.ui.productList.Category
import com.example.basicgetirclone.ui.productList.ProductListPageServiceInterface
import com.example.basicgetirclone.ui.productList.ResultData
import com.example.basicgetirclone.ui.productList.SubCategory

interface CategoryRepoInterface {
    var categories: MutableLiveData<List<Category>>
    var subCategories : MutableLiveData<List<SubCategory>>
    suspend  fun getAllCategories()
    fun  uploadSubCategories(id: Int)
    fun getItemCountCategoryAdapter() : Int
    fun getItemCountSubCategoryAdapter() :  Int
    fun onBindViewHolderCategoryAdapter(position:Int) : Pair<Category,Boolean>
    fun onBindViewHolderSubCategoryAdapter(position: Int) : Pair<SubCategory,Boolean>
    fun onClickCategory(id:Int)
    fun onClickSubCategory(id:Int)
}

class CategoryRepo(private var productListService: ProductListPageServiceInterface) : CategoryRepoInterface {
    override var categories: MutableLiveData<List<Category>> = MutableLiveData()
    override var subCategories :  MutableLiveData<List<SubCategory>> = MutableLiveData()
    private var selectedCategoryId: Int = 1
    private var selectedSubCategoryId:Int = 1
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

    override fun  uploadSubCategories(id: Int){
        categories.observeForever { list ->
            val subCategoryFilter = list.first { it.id == id }.subCategory

            subCategories.value = subCategoryFilter

         }
    }

    private  fun getCategoriesWithoutNullAble() : List<Category> {
        val categoryList = categories.value ?: emptyList()
        return  categoryList
    }

    private  fun getSubCategoryWithoutNullAble() : List<SubCategory>{
        val list = subCategories.value ?: emptyList()
        return  list
    }

    override fun getItemCountCategoryAdapter() : Int {
        val categories =  getCategoriesWithoutNullAble()
        return  categories.size
    }

    override fun getItemCountSubCategoryAdapter() :  Int {
        val list = getSubCategoryWithoutNullAble()
        return list.count()
    }

    override  fun onBindViewHolderCategoryAdapter(position:Int) : Pair<Category,Boolean> {
        val categoryList =  getCategoriesWithoutNullAble()
        val  category:Category = categoryList[position]
        val visibleState:Boolean = category.id == selectedCategoryId
        return  Pair(category,visibleState)
    }
    override fun onBindViewHolderSubCategoryAdapter(position: Int) : Pair<SubCategory,Boolean> {
        val list = getSubCategoryWithoutNullAble()
        val subCategory:SubCategory = list[position]
        val visibleState = subCategory.id == selectedSubCategoryId
        return Pair(subCategory,visibleState)
    }

    override fun onClickCategory(id: Int) {
        selectedCategoryId = id
        uploadSubCategories(id)
        subCategories.observeForever { list ->
            selectedSubCategoryId = list.first().id
        }
    }

   override fun onClickSubCategory(id:Int) {
        selectedSubCategoryId = id
        uploadSubCategories(id)
    }
}