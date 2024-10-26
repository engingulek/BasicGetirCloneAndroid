package com.example.basicgetirclone.di

import com.example.basicgetirclone.repo.CategoryRepo
import com.example.basicgetirclone.repo.CategoryRepoInterface
import com.example.basicgetirclone.repo.ProductDaoRepo
import com.example.basicgetirclone.repo.ProductRepoInterface
import com.example.basicgetirclone.retrofit.ApiUtils
import com.example.basicgetirclone.retrofit.CategoryDao
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
    fun provideProductRepo(cdo:CategoryDao) : ProductRepoInterface {
        val serivce : ProductListPageServiceInterface = ProductListPageService(cdo)
        val repo : ProductRepoInterface = ProductDaoRepo(serivce)
        return  repo
    }

    @Provides
    @Singleton
    fun provşdeCatagoryRepo(cdo:CategoryDao) : CategoryRepoInterface {
        val serivce : ProductListPageServiceInterface = ProductListPageService(cdo)
        val repo : CategoryRepoInterface = CategoryRepo(serivce)
        return  repo
    }

    @Provides
    @Singleton
    fun provideCategoryDao() : CategoryDao {

        return ApiUtils.getCategoiresDao()
    }
}