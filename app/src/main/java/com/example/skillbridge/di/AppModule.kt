package com.example.skillbridge.di

import android.content.Context
import androidx.room.Room
import com.example.skillbridge.data.local.AppDatabase
import com.example.skillbridge.data.local.SavedJobDao
import com.example.skillbridge.data.remote.SkillBridgeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module @InstallIn(SingletonComponent::class)
object AppModule {
    @Provides @Singleton fun database(@ApplicationContext context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, "skillbridge.db").build()
    @Provides fun savedJobDao(database: AppDatabase): SavedJobDao = database.savedJobDao()
    @Provides @Singleton fun api(): SkillBridgeApiService {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
        return Retrofit.Builder().baseUrl("http://10.0.2.2:8080/").client(OkHttpClient.Builder().addInterceptor(logger).build()).addConverterFactory(GsonConverterFactory.create()).build().create(SkillBridgeApiService::class.java)
    }
}
