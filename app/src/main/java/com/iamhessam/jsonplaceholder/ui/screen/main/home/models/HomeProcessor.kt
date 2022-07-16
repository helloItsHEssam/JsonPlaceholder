package com.iamhessam.jsonplaceholder.ui.screen.main.home.models

import com.iamhessam.jsonplaceholder.data.Repository
import com.iamhessam.jsonplaceholder.mvi.MviProcessor
import com.iamhessam.jsonplaceholder.mvi.MviProcessorType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

sealed class HomeProcessorType : MviProcessorType {

    object Refresh : HomeProcessorType()
    object Init : HomeProcessorType()
    object Cancel : HomeProcessorType()
}

class HomeProcessor(override var processorType: HomeProcessorType) :
    MviProcessor<HomeResult, HomeProcessorType> {

    private lateinit var repository: Repository

    override fun injectRepository(repository: Repository) {
        this.repository = repository
    }

    override suspend fun mapToResult(): Flow<HomeResult> {
        return when (processorType) {
            is HomeProcessorType.Refresh -> handlerRefresh()
            is HomeProcessorType.Init -> handlerInit()
            is HomeProcessorType.Cancel -> handlerCancel()
        }
    }

    private suspend fun handlerRefresh(): Flow<HomeResult> {
        return repository
            .local
            .prefsStore
            .isNightMode()
            .onStart {
                HomeResult.Loading
            }
            .onEach { delay(10_000) }
            .map {
                HomeResult.Success("Hello Response Success $it")
            }
    }

    private suspend fun handlerInit(): Flow<HomeResult> {
        return flow {
            emit(HomeResult.Loading)
            delay(5_000)
            emit(HomeResult.Error("Hello Message Error"))
        }
    }

    private suspend fun handlerCancel(): Flow<HomeResult> {
        return flow {
            emit(HomeResult.Loading)
            delay(5_000)
            emit(HomeResult.Error("Hello Message Error"))
        }
    }
}