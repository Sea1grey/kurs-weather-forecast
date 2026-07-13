package com.sergey.weatherforecast.data.remote

import com.sergey.weatherforecast.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApi {

    @GET("v1/city")
    suspend fun searchCity(
        @Query("name") city: String
    ): List<CityResponse>

    companion object {

        fun create(): CityApi {

            val client = OkHttpClient.Builder()
                .addInterceptor { chain ->

                    val request: Request = chain.request()
                        .newBuilder()
                        .addHeader(
                            "X-Api-Key",
                            BuildConfig.API_NINJAS_KEY
                        )
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