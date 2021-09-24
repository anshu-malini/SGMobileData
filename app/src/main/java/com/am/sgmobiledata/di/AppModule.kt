package com.am.sgmobiledata.di

import android.content.Context
import com.am.sgmobiledata.data.remote.MobileDataService
import com.am.sgmobiledata.data.remote.RemoteDataSource
import com.am.sgmobiledata.data.repository.Repository
import com.am.sgmobiledata.data.room.AppDatabase
import com.am.sgmobiledata.data.room.MobileDataDao
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://data.gov.sg/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(provideClient())
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideDataService(retrofit: Retrofit): MobileDataService =
        retrofit.create(MobileDataService::class.java)

    @Singleton
    @Provides
    fun provideMobileRemoteDataSource(dataService: MobileDataService) =
        RemoteDataSource(dataService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideDataDao(db: AppDatabase) = db.mobileDataDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource, localDataSource: MobileDataDao) =
        Repository(remoteDataSource, localDataSource)

    @Provides
    fun provideClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }
}