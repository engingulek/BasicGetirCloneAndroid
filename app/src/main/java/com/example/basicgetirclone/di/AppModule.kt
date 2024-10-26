package com.example.basicgetirclone.di

import com.example.basicgetirclone.repo.ProductDaoRepo
import com.example.basicgetirclone.repo.ProductDaoRepoInterface
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
    fun provideProductRepo(cdo:CategoryDao) : ProductDaoRepoInterface {
        val serivce : ProductListPageServiceInterface = ProductListPageService(cdo)
        val repo : ProductDaoRepoInterface = ProductDaoRepo(serivce)
        return  repo
    }

    @Provides
    @Singleton
    fun provideCategoryDao() : CategoryDao {

        return ApiUtils.getCategoiresDao()
    }
}