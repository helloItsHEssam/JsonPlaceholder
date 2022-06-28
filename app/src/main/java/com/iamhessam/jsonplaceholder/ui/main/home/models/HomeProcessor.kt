package com.iamhessam.jsonplaceholder.ui.main.home.models

import com.iamhessam.jsonplaceholder.ui.main.mvi.MviProcessor
import kotlinx.coroutines.delay

sealed class HomeProcessor : MviProcessor<HomeResult> {
    // TODO: - add Hilt Di For pass repository
//    private val repository: Repository = Repository()

    object Refresh : HomeProcessor()
    object Init : HomeProcessor()
    object Cancel : HomeProcessor()

    override suspend fun mapToResult(): HomeResult = this.process()
    private suspend fun process(): HomeResult = when (this) {
        is Refresh -> handlerRefresh()
        is Init -> handlerInit()
        is Cancel -> handlerCancel()
    }

    private suspend fun handlerRefresh(): HomeResult {
        delay(5_000)
        return HomeResult.Success("Hello Response Success")
    }

    private fun handlerInit(): HomeResult {
        return HomeResult.Error("Hello Message Error")
    }

    private fun handlerCancel(): HomeResult {
        return HomeResult.Loading
    }

//    private fun refreshHandle(): Flow<HomeResult> {
//        return this.repository.getFakeData()
//            .onStart { HomeResult.Loading }
//            .map { HomeResult.Success(it) }
//            .catch { e -> HomeResult.Error(e.message ?: "Error") }
//            .cancellable()
//            .flowOn(Dispatchers.IO)
//    }
}