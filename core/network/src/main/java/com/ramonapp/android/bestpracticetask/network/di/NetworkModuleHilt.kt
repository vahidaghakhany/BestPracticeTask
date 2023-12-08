package com.ramonapp.android.bestpracticetask.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ramonapp.android.bestpracticetask.network.BuildConfig
import com.ramonapp.android.bestpracticetask.network.DefaultCallAdapterFactory
import com.ramonapp.android.bestpracticetask.network.NetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModuleHilt {


    private const val CONNECTION_TIME_OUT = 15L
    private const val READ_TIME_OUT = 15L
    private const val WRITE_TIME_OUT = 15L



    @Provides
    @Singleton
    internal fun provideNetworkApi(retrofit: Retrofit): NetworkApi {
        return retrofit.create(NetworkApi::class.java)
    }

    @Singleton
    @Provides
    internal fun provideRetrofitBuilder(gson: Gson, client: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(DefaultCallAdapterFactory())
            .client(client)
            .build()

    @Singleton
    @Provides
    internal fun provideGson() = GsonBuilder()
        .setLenient()
        .serializeNulls()
        .create()

    @Singleton
    @Provides
    internal fun provideOkhttpClient() = OkHttpClient.Builder()
        .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
        .build()

}