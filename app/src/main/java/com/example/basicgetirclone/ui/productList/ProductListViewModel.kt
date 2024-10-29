package com.example.basicgetirclone.ui.productList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicgetirclone.repo.CartRepoInterface
import com.example.basicgetirclone.repo.CategoryRepoInterface
import com.example.basicgetirclone.repo.ProductRepoInterface
import com.example.basicgetirclone.ui.cart.CartProduct
import com.example.basicgetirclone.ui.cart.ProductRequest
import com.example.basicgetirclone.ui.productList.models.Category
import com.example.basicgetirclone.ui.productList.models.Product
import com.example.basicgetirclone.ui.productList.models.SubCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private val productDaoRepo: ProductRepoInterface,
                                               private val categoryRepo: CategoryRepoInterface,
                                               private val cartRepo:CartRepoInterface
    ) :
    ViewModel() {

    var categories = MutableLiveData<List<Category>>()
    var subCategory = MutableLiveData<List<SubCategory>>()
    var products = MutableLiveData<List<Product>>()
    private var cartProduct = MutableLiveData<List<CartProduct>>()
    var total = MutableLiveData<Double>(0.0)
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

        cartRepo.cartProducts.observeForever { list ->
            cartProduct.value = list
        }
        cartRepo.totalAmount.observeForever {
            total.value = it
        }

        uploadSubCategory(selectedCategoryId)
        fetchProduct(selectedSubCategoryId)
        fetcHCartRepo()
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

    private fun fetcHCartRepo(){
        viewModelScope.launch {
            cartRepo.getCartProducts(1)
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

    fun getItemCountProductAdapter() : Int{
        return productDaoRepo.getItemCount()
    }

    fun onBindViewHolderProductAdapter(position:Int) : Pair<Product,Boolean> {
        val productL = productDaoRepo.onBindViewHolder(position)
        val list = cartProduct.value ?: emptyList()
        val matchedProduct = list.firstOrNull { it.productID == productL.id }
        if (matchedProduct != null){
            productL.piece = matchedProduct.quantity
            return Pair(productL,true)
        }else{
            return   Pair(productL,false)
        }
    }

    fun onClickAdd(productId:Int,userId:Int){
        val requestProduct = ProductRequest(userId,productId)
        viewModelScope.launch {
            cartRepo.addProductToCart(requestProduct)
        }
    }

    fun decreaseProduct(productId:Int){

        val list = cartProduct.value ?: emptyList()
        val matchedProduct = list.first { it.productID == productId }
        viewModelScope.launch {
            cartRepo.decreaseProductFromCart(matchedProduct.id)
        }
    }
}



