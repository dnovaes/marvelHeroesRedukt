package com.dnovaes.marvelheroes.services.base

import com.dnovaes.marvelheroes.models.Character
import com.dnovaes.marvelheroes.models.marvelApi.ServerResponse
import com.dnovaes.marvelheroes.services.RouteConstants.CHARACTERS
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {
    @GET(CHARACTERS)
    fun getCharacters(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<ServerResponse<Character>>
}