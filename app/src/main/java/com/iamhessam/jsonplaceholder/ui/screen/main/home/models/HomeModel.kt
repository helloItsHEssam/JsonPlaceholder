package com.iamhessam.jsonplaceholder.ui.screen.main.home.models

import com.iamhessam.jsonplaceholder.data.Repository
import com.iamhessam.jsonplaceholder.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeModel @Inject constructor(repo: Repository) :
    BaseViewModel<HomeResult, HomeProcessorType, HomeProcessor, HomeAction, HomeIntent, HomeViewState>(
        HomeViewState(),
        HomeIntent.Initial,
        HomeViewState.reducer,
        repo
    )