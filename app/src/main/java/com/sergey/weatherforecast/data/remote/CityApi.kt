package com.sergey.weatherforecast.data.remote

import com.sergey.weatherforecast.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.logging.HttpLoggingInterceptor

interface CityApi {

    @GET("v1/city")
    suspend fun searchCity(
        @Query("name") city: String,
    ): List<CityResponse>

    companion object {

        fun create(): CityApi {

            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor { chain ->
                    val request = chain.request()
                        .newBuilder()
                        .addHeader("X-Api-Key", BuildConfig.API_NINJAS_KEY)
                        .build()

                    chain.proceed(request)
                }
                .build()

            return Retrofit.Builder()
                .baseUrl("https://api.api-ninjas.com/")
                .client(client)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .build()
                .create(CityApi::class.java)
        }
    }
}