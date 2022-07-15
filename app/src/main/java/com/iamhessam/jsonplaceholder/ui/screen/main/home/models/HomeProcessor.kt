package com.iamhessam.jsonplaceholder.ui.screen.main.home.models

import com.iamhessam.jsonplaceholder.data.Repository
import com.iamhessam.jsonplaceholder.mvi.MviProcessor
import com.iamhessam.jsonplaceholder.mvi.MviProcessorType
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

sealed class HomeProcessorType : MviProcessorType {

    object Refresh : HomeProcessorType()
    object Init : HomeProcessorType()
    object Cancel : HomeProcessorType()
}

class HomeProcessor(override var processorType: HomeProcessorType) :
    MviProcessor<HomeResult, HomeProcessorType> {

    lateinit var repository: Repository

    override fun injectRepository(repository: Repository) {
        this.repository = repository
    }

    override suspend fun mapToResult(): HomeResult {
        return when (this.processorType) {
            is HomeProcessorType.Refresh -> handlerRefresh()
            is HomeProcessorType.Init -> handlerInit()
            is HomeProcessorType.Cancel -> handlerCancel()
        }
    }

    private suspend fun handlerRefresh(): HomeResult {
        delay(5_000)
        return HomeResult.Success("Hello Response Success")
    }

    private fun handlerInit(): HomeResult {
        runBlocking {
            this@HomeProcessor.repository.local.prefsStore.updateNightMode()
        }

        return HomeResult.Error("Hello Message Error")
    }

    private fun handlerCancel(): HomeResult {
        return HomeResult.Loading
    }
}