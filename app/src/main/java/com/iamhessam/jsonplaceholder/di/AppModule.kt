package com.iamhessam.jsonplaceholder.di

import android.content.Context
import androidx.room.Room
import com.iamhessam.jsonplaceholder.data.local.db.room.AppDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    // room DB
    @Provides
    @Singleton
    fun provideAppDB(@ApplicationContext appContext: Context): AppDB {
        return Room
            .databaseBuilder(appContext, AppDB::class.java, "db")
            .build()
    }

    // network Connectivity di
}