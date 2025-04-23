package com.example.minerush.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClient {
    var BASE_URL: String = "   https://0bba-45-251-35-84.ngrok-free.app "


    val instance: com.example.minerush.api.ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(com.example.minerush.api.RetrofitClient.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(com.example.minerush.api.ApiService::class.java)
    }


    val retrofit: Retrofit
        get() {
            // Create an HTTP logging interceptor for debugging
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            // Configure OkHttpClient with a timeout of 30 minutes for all actions
            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES) // Connection timeout
                .readTimeout(2, TimeUnit.MINUTES) // Read timeout
                .writeTimeout(2, TimeUnit.MINUTES) // Write timeout
                .addInterceptor(loggingInterceptor) // Add the logging interceptor
                .build()

            // Build Retrofit instance with the OkHttpClient
            return Retrofit.Builder()
                .baseUrl(com.example.minerush.api.RetrofitClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }

    //val `interface`: Interface
      //  get() = retrofit.create(Interface::class.java)
}


