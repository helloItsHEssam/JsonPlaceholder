package com.iamhessam.jsonplaceholder.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.iamhessam.jsonplaceholder.data.local.datastore.preferences.PrefsStore
import com.iamhessam.jsonplaceholder.data.local.datastore.preferences.PrefsStoreImpl
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

    // dataStore
    private val Context.dataStore by preferencesDataStore("app_preferences")

    @Provides
    @Singleton
    fun provideAppDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return appContext.dataStore
    }

    // network Connectivity di
}