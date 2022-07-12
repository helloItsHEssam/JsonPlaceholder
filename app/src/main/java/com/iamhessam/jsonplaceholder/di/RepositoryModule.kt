package com.iamhessam.jsonplaceholder.di

import com.iamhessam.jsonplaceholder.data.Repository
import com.iamhessam.jsonplaceholder.data.RepositoryImpl
import com.iamhessam.jsonplaceholder.data.local.LocalRepository
import com.iamhessam.jsonplaceholder.data.local.LocalRepositoryImpl
import com.iamhessam.jsonplaceholder.data.local.datastore.preferences.PrefsStore
import com.iamhessam.jsonplaceholder.data.local.datastore.preferences.PrefsStoreImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    @Singleton
    abstract fun bindPrefs(prefsStore: PrefsStoreImpl): PrefsStore

    @Module(includes = [RepositoryModule::class])
    @InstallIn(SingletonComponent::class)
    class ProvideModule {

        // local
        @Provides
        fun provideLocalRepository(): LocalRepository {
            return LocalRepositoryImpl()
        }

        // remote
    }
}