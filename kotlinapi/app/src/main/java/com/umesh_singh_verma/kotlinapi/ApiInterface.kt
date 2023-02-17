package com.umesh_singh_verma.kotlinapi

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("characters")
    fun getData(): Call<List<MyDataItem>>
}