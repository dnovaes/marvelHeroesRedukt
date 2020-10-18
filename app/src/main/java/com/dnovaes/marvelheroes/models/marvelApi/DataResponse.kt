package com.dnovaes.marvelheroes.models.marvelApi

data class DataResponse<T>(
    val offset: Int,
    val limit: Int,
    val total: String,
    val count: String,
    val results: List<T>
)

