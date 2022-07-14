package com.iamhessam.jsonplaceholder.ui.screen.main.home.models

import com.iamhessam.jsonplaceholder.mvi.BaseViewModel
import kotlinx.coroutines.runBlocking


class HomeModel :
    BaseViewModel<HomeResult, HomeProcessor, HomeAction, HomeIntent, HomeViewState>(
        HomeViewState(),
        HomeIntent.Initial,
        HomeViewState.reducer
    ) {

        init {
            runBlocking {
                repo.local.prefsStore.updateNightMode()
            }
        }
    }