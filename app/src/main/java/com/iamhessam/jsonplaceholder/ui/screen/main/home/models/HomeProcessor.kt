package com.iamhessam.jsonplaceholder.ui.screen.main.home.models

import com.iamhessam.jsonplaceholder.data.Repository
import com.iamhessam.jsonplaceholder.mvi.MviProcessor
import com.iamhessam.jsonplaceholder.mvi.MviProcessorType
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

sealed class HomeProcessorType : MviProcessorType {
    object FetchComment : HomeProcessorType()
    object Init: HomeProcessorType()
}

class HomeProcessor(override var processorType: HomeProcessorType) :
    MviProcessor<HomeResult, HomeProcessorType> {

    private lateinit var repository: Repository

    override fun injectRepository(repository: Repository) {
        this.repository = repository
    }

    override suspend fun mapToResult(): Flow<HomeResult> {
        return when (processorType) {
            is HomeProcessorType.FetchComment -> fetchComment()
            is HomeProcessorType.Init -> handlerInit()
        }
    }

    private suspend fun fetchComment(): Flow<HomeResult> {
        return flow {
            emit(HomeResult.Loading)
            val response = repository.remote.http.httpClient.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "jsonplaceholder.typicode.com"
                    path("posts/1/comments")
                }
            }
            emit(HomeResult.Success(response.body()))
        }
    }

    private suspend fun handlerInit(): Flow<HomeResult> {
        return flow {
            emit(HomeResult.Loading)
            delay(5_000)
            emit(HomeResult.Error("Hello Message Error"))
        }
    }
}