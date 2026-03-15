package com.sraccelerator.easyorder.data.remote.di

import com.sraccelerator.easyorder.data.remote.api.EasyOrderApiService
import com.sraccelerator.easyorder.data.remote.network.EasyOrderApiNetworkAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    private const val BASE_URL = "https://lucasfaiska.github.io/easyorder-mock-api/"
    private const val TIMEOUT_SECONDS = 15L

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS).build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(EasyOrderApiNetworkAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideEasyOrderApiService(retrofit: Retrofit): EasyOrderApiService = retrofit.create(EasyOrderApiService::class.java)
}