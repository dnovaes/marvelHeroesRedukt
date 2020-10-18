package com.dnovaes.marvelheroes.models.marvelApi

data class ServerResponse<T>(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: DataResponse<T>
)
