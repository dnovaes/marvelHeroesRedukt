package com.dnovaes.marvelheroes.services

import com.dnovaes.marvelheroes.BuildConfig
import com.dnovaes.marvelheroes.extensions.md5
import com.dnovaes.marvelheroes.models.Character
import com.dnovaes.marvelheroes.models.marvelApi.ServerResponse
import com.dnovaes.marvelheroes.services.base.BaseApi
import com.dnovaes.marvelheroes.services.base.MarvelService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.sql.Timestamp

object MarvelServiceApi : BaseApi() {

    override val serviceURL = "https://gateway.marvel.com:443/v1/public/"

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okhttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    override val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(serviceURL)
        .client(okhttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(MarvelService::class.java)
    private const val APIKEY = "f68005f184c4d81cb33ddd7836eb7f28"
    private const val LIMIT = 4

    fun getCharacters(offset: Int, onSuccess: ((ServerResponse<Character>) -> Unit), onFail: ((String) -> Unit)) {
        val timeStamp = Timestamp(System.currentTimeMillis()).toString()
        val privateKey = BuildConfig.PRIVATE_KEY
        val md5Hash = "$timeStamp$privateKey$APIKEY".md5()

        service.getCharacters(timeStamp, APIKEY, md5Hash, offset, LIMIT).enqueue(handleResponse { content, errorMessage ->
            errorMessage?.let {
                Timber.v("Failed on retrieving characters data: $it")
                onFail.invoke(it)
            } ?: apply {
                content?.let { response ->
                    onSuccess.invoke(response)
                }
            }
        })
    }
}
