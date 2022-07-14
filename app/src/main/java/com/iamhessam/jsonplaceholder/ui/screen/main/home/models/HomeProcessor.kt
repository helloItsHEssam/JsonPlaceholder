package com.iamhessam.jsonplaceholder.ui.screen.main.home.models

import android.util.Log
import com.iamhessam.jsonplaceholder.data.Repository
import com.iamhessam.jsonplaceholder.mvi.MviProcessor
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

sealed class HomeProcessor : MviProcessor<HomeResult>() {

    @Inject lateinit var repository: Repository

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
        runBlocking {
//            this@HomeProcessor.repository.local.prefsStore.updateNightMode()
        }

        return HomeResult.Error("Hello Message Error")
    }

    private fun handlerCancel(): HomeResult {
        return HomeResult.Loading
    }
}