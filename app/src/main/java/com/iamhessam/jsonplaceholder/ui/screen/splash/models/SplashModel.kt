package com.iamhessam.jsonplaceholder.ui.screen.splash.models

import com.iamhessam.jsonplaceholder.data.Repository
import com.iamhessam.jsonplaceholder.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashModel @Inject constructor(repo: Repository) :
    BaseViewModel<SplashResult, SplashProcessorType, SplashProcessor, SplashAction, SplashIntent, SplashViewState>(
        SplashViewState.init,
        SplashIntent.ReadingTheme,
        SplashViewState.reducer,
        repo
    )