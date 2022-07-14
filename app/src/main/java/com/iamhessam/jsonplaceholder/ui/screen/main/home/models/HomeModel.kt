package com.iamhessam.jsonplaceholder.ui.screen.main.home.models

import com.iamhessam.jsonplaceholder.mvi.BaseViewModel

class HomeModel :
    BaseViewModel<HomeResult, HomeProcessor, HomeAction, HomeIntent, HomeViewState>(
        HomeViewState(),
        HomeIntent.Initial,
        HomeViewState.reducer
    )