package com.iamhessam.jsonplaceholder.di

import com.iamhessam.jsonplaceholder.utils.settings.theme.UserSettings
import com.iamhessam.jsonplaceholder.utils.settings.theme.UserSettingsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserSettingModule {

    @Binds
    @Singleton
    abstract fun bindUserSettings(userSettingsImpl: UserSettingsImpl): UserSettings
}