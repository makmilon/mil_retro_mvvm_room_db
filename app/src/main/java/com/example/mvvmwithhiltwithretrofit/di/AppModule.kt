package com.example.mvvmwithhiltwithretrofit.di

import android.app.Application
import com.example.mvvmwithhiltwithretrofit.Repository.Repository
import com.example.mvvmwithhiltwithretrofit.favorite_item.AppDatabase
import com.example.mvvmwithhiltwithretrofit.favorite_item.ProductDao
import com.example.mvvmwithhiltwithretrofit.netWork.RetroInstance
import com.example.mvvmwithhiltwithretrofit.viewmodel.MyViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://run.mocky.io/"

    @Singleton
    @Provides
    fun getRetroServiceInstance(retrofit: Retrofit): RetroInstance{
        return retrofit.create(RetroInstance::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit{

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getAppDB(context: Application): AppDatabase {
        return AppDatabase.getAppDB(context)
    }

    @Singleton
    @Provides
    fun getDao(appDB: AppDatabase): ProductDao {
        return appDB.getDAO()
    }


}
