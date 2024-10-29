package com.example.basicgetirclone.di

import com.example.basicgetirclone.repo.CartRepo
import com.example.basicgetirclone.repo.CartRepoInterface
import com.example.basicgetirclone.repo.CategoryRepo
import com.example.basicgetirclone.repo.CategoryRepoInterface
import com.example.basicgetirclone.repo.ProductDaoRepo
import com.example.basicgetirclone.repo.ProductRepoInterface
import com.example.basicgetirclone.retrofit.ApiUtils
import com.example.basicgetirclone.retrofit.BaseDao
import com.example.basicgetirclone.ui.cart.CartService
import com.example.basicgetirclone.ui.cart.CartServiceInterface

import com.example.basicgetirclone.ui.productList.ProductListPageService
import com.example.basicgetirclone.ui.productList.ProductListPageServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideProductRepo(bdo:BaseDao) : ProductRepoInterface {
        val serivce : ProductListPageServiceInterface = ProductListPageService(bdo)
        val repo : ProductRepoInterface = ProductDaoRepo(serivce)
        return  repo
    }

    @Provides
    @Singleton
    fun provşdeCatagoryRepo(bdo:BaseDao) : CategoryRepoInterface {
        val serivce : ProductListPageServiceInterface = ProductListPageService(bdo)
        val repo : CategoryRepoInterface = CategoryRepo(serivce)
        return  repo
    }

    @Provides
    @Singleton
    fun provideCartRepo(bdo: BaseDao) : CartRepoInterface {
        val service : CartServiceInterface = CartService(bdo)
        val repo: CartRepoInterface = CartRepo(service)
        return  repo
    }



    @Provides
    @Singleton
    fun provideCategoryDao() : BaseDao {

        return ApiUtils.getCategoiresDao()
    }
}