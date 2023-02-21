package com.example.meta_assess.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.giphy.com/v1/gifs/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface GiphyApiService {
    @GET("trending")
    suspend fun getPhotos(@Query(value="api_key", encoded=true) apiKey: String): GiphyResponse
}

object GiphyApi {
    val retrofitService: GiphyApiService by lazy { retrofit.create(GiphyApiService::class.java) }
}