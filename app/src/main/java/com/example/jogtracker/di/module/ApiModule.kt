package com.example.jogtracker.di.module

import com.example.data.constants.Constants
import com.example.data.remote.api.JogsApi
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

val apiModule: Module = module {

    single { provideRetrofit() }
    single { provideWeatherApi(get()) }
}

fun provideWeatherApi(retrofit: Retrofit): JogsApi =
    retrofit.create(JogsApi::class.java)

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(
            OkHttpClient().newBuilder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build()
        )
        .build()
}

