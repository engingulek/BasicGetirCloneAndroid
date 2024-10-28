package com.example.basicgetirclone.retrofit

class ApiUtils {
    companion object{
        val BASE_URL = "http://10.0.2.2:8080/api/"

        fun getCategoiresDao() : BaseDao {
            return RetrofitClient.getClient(BASE_URL).create(BaseDao::class.java)
        }
    }
}

sealed class ResultData<out T> {
    data class Success<out T>(val data: T) : ResultData<T>()
    data class Error(val error: NetworkError) : ResultData<Nothing>()
}