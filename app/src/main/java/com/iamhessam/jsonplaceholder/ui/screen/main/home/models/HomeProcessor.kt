package com.iamhessam.jsonplaceholder.ui.screen.main.home.models

import com.iamhessam.jsonplaceholder.data.Repository
import com.iamhessam.jsonplaceholder.mvi.MviProcessor
import com.iamhessam.jsonplaceholder.mvi.MviProcessorType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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
        return flow {
            emit(HomeResult.Loading)
            when (processorType) {
                is HomeProcessorType.Refresh -> {
                    emit(handlerRefresh())
                }
                is HomeProcessorType.Init -> {
                    emit(handlerInit())
                }
                is HomeProcessorType.Cancel -> handlerCancel()
            }
        }
    }

    private suspend fun handlerRefresh(): HomeResult {
        delay(5_000)
        return HomeResult.Success("Hello Response Success")
    }

    private suspend fun handlerInit(): HomeResult {
        delay(5_000)
        return HomeResult.Error("Hello Message Error")
    }

    private suspend fun handlerCancel(): HomeResult {
        delay(5_000)
        return HomeResult.Error("Hello Message Error")
    }
}