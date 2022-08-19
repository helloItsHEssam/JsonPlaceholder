package com.iamhessam.jsonplaceholder.ui.screen.main.home.models

import com.iamhessam.jsonplaceholder.data.Repository
import com.iamhessam.jsonplaceholder.data.remote.http.ktor.dto.CommentDTO
import com.iamhessam.jsonplaceholder.data.remote.http.ktor.router.Post
import com.iamhessam.jsonplaceholder.mvi.MviProcessor
import com.iamhessam.jsonplaceholder.mvi.MviProcessorType
import com.iamhessam.jsonplaceholder.utils.exception.ArashniaException
import com.iamhessam.jsonplaceholder.utils.extra.mapper.CommentMapper
import io.ktor.client.call.*
import io.ktor.client.plugins.resources.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

sealed class HomeProcessorType : MviProcessorType {
    object FetchComment : HomeProcessorType()
    object Init : HomeProcessorType()
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
            val path = Post.Id.Comments(Post.Id(id = 1))
            try {
                val response: List<CommentDTO> =
                    repository.remote.http.httpClient.get(path) {
                        url {
                            parameters.append("id", "1")
                        }
                    }.body()

                val commentEntities = CommentMapper().mapDtoListToEntityList(response)
                emit(HomeResult.Success(commentEntities))
            } catch (e: ArashniaException) {
                emit(HomeResult.Error(e))
            }
        }
    }

    private suspend fun handlerInit(): Flow<HomeResult> {
        return flow {
            emit(HomeResult.Loading)
            delay(5_000)
            emit(HomeResult.Error(ArashniaException.NotFoundException("error")))
        }
    }
}