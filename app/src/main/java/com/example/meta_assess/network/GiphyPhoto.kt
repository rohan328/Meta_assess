package com.example.meta_assess.network

import com.squareup.moshi.Json

data class GiphyPhotoData(
    val url : String
)

data class GiphyPhotoOriginal(
    @Json(name = "original") val original : GiphyPhotoData
)

data class GiphyItem(
    val id: String,
    val title: String,
    @Json(name = "images") val images : GiphyPhotoOriginal
)

data class GiphyResponse(
    @Json(name = "data") val data : List<GiphyItem>
)