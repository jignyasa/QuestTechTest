package com.example.questtechtest.remote

import com.example.questtechtest.model.PostList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {
    @GET("posts")
    fun getListData(): Single<ArrayList<PostList>>

    @GET("posts/{id}")
    fun getPurticularPost(@Path("id") id:Int): Single<PostList>
}