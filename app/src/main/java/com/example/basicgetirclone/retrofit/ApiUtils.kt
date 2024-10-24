package com.example.basicgetirclone.retrofit

class ApiUtils {
    companion object{
        val BASE_URL = "http://10.0.2.2:8080/api/"

        fun getCategoiresDao() : CategoryDao {
            return RetrofitClient.getClient(BASE_URL).create(CategoryDao::class.java)
        }
    }
}