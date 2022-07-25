package com.iamhessam.jsonplaceholder.di

import com.iamhessam.jsonplaceholder.utils.settings.theme.UISettings
import com.iamhessam.jsonplaceholder.utils.settings.theme.UISettingsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UISettingModule {

    @Binds
    @Singleton
    abstract fun bindUserSettings(userSettingsImpl: UISettingsImpl): UISettings
}