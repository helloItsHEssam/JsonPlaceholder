package com.iamhessam.jsonplaceholder.ui.main.home.models

import com.iamhessam.jsonplaceholder.ui.main.mvi.BaseViewModel

class HomeModel: BaseViewModel<HomeResult, HomeProcessor, HomeAction, HomeIntent, HomeViewState>(
    HomeViewState(),
    HomeIntent.Initial,
    HomeViewState.reducer
)