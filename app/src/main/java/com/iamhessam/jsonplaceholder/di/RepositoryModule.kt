package com.iamhessam.jsonplaceholder.di

import android.content.Context
import com.iamhessam.jsonplaceholder.data.local.LocalRepository
import com.iamhessam.jsonplaceholder.data.local.db.room.AppDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

//    @Binds
//    @Singleton
//    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository

//    @Binds
//    @Singleton
//    abstract fun bindLocalRepository(localRepository: LocalRepositoryImpl): LocalRepository

    //    @Singleton
    @Singleton
    @Provides
    fun localRepository(@ApplicationContext appContext: Context): LocalRepository {
        val database = AppDB.getInstance(appContext)
        return LocalRepository(database)
    }

//    @Module(includes = [RepositoryModule::class])
//    @InstallIn(SingletonComponent::class)
//    class ProvideModule {
//
//        @Singleton
//        @Provides
//        fun provideAppDB(@ApplicationContext appContext: Context): AppDB {
//            return AppDB.getInstance(appContext)
//        }
//    }


//    @Binds
//    @Singleton
//    abstract fun bindPrefs(prefsStore: PrefsStoreImpl): PrefsStore

//    @Module(includes = [RepositoryModule::class])
//    @InstallIn(SingletonComponent::class)
//    class ProvideModule {
//
//        // local
////        @Provides
////        fun provideLocalRepository(@ApplicationContext appContext: Context): LocalRepository {
////            val appDB = Room
////                .databaseBuilder(appContext, AppDB::class.java, "db")
////                .build()
////            val prefsStore = PrefsStoreImpl()
////            return LocalRepositoryImpl(appDB, prefsStore)
////        }
//
////        @Provides
////        @Singleton
////        fun provideAppDB(@ApplicationContext appContext: Context): AppDB {
////            return Room
////                .databaseBuilder(appContext, AppDB::class.java, "db")
////                .build()
////        }
//
//        // remote
//    }
}