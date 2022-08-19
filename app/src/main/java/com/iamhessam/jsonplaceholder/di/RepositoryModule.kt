package com.iamhessam.jsonplaceholder.di

import com.iamhessam.jsonplaceholder.data.Repository
import com.iamhessam.jsonplaceholder.data.RepositoryImpl
import com.iamhessam.jsonplaceholder.data.local.LocalRepository
import com.iamhessam.jsonplaceholder.data.local.LocalRepositoryImpl
import com.iamhessam.jsonplaceholder.data.local.datastore.preferences.PrefsStore
import com.iamhessam.jsonplaceholder.data.local.datastore.preferences.PrefsStoreImpl
import com.iamhessam.jsonplaceholder.data.remote.RemoteRepository
import com.iamhessam.jsonplaceholder.data.remote.RemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository

    // local
    @Module(includes = [RepositoryModule::class])
    @InstallIn(SingletonComponent::class)
    interface LocalProvideModule {

        @Binds
        @Singleton
        fun bindLocalRepository(localRepository: LocalRepositoryImpl): LocalRepository

        @Binds
        @Singleton
        fun bindPrefsStore(prefsStore: PrefsStoreImpl): PrefsStore
    }

    // remote
    @Module(includes = [RepositoryModule::class])
    @InstallIn(SingletonComponent::class)
    interface RemoteProvideModule {

        @Binds
        @Singleton
        fun bindRemoteRepository(remoteRepository: RemoteRepositoryImpl): RemoteRepository
    }
}