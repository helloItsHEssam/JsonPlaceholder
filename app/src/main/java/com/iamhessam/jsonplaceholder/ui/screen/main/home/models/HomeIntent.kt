package com.iamhessam.jsonplaceholder.ui.screen.main.home.models

import com.iamhessam.jsonplaceholder.data.local.db.room.entity.CommentEntity
import com.iamhessam.jsonplaceholder.mvi.*
import com.iamhessam.jsonplaceholder.utils.exception.ArashniaException

sealed class HomeResult : MviResult {
    object Loading : HomeResult()
    data class Error(val error: ArashniaException) : HomeResult()
    data class Success(val response: List<CommentEntity>) : HomeResult()
}

sealed class HomeAction : MviAction<HomeResult, HomeProcessorType, HomeProcessor> {
    object Init : HomeAction()
    data class LoadComment(val commentId: Int) : HomeAction()
    object FetchComment : HomeAction()

    override fun mapToProcessor(): HomeProcessor = when (this) {
        is LoadComment -> HomeProcessor(HomeProcessorType.Init)
        is Init -> HomeProcessor(HomeProcessorType.Init)
        is FetchComment -> HomeProcessor(HomeProcessorType.FetchComment)
    }
}

sealed class HomeIntent : MviIntent<HomeResult, HomeProcessorType, HomeProcessor, HomeAction> {
    object Initial : HomeIntent()
    data class LoadComment(val commentId: Int) : HomeIntent()
    object FetchComment : HomeIntent()

    override fun hashCode(): Int = when (this) {
        is Initial -> 1
        is FetchComment -> 2
        is LoadComment -> 3 + commentId.hashCode()
    }

    override fun mapToAction(): HomeAction = when (this) {
        is Initial -> HomeAction.Init
        is FetchComment -> HomeAction.FetchComment
        is LoadComment -> HomeAction.LoadComment(this.commentId)
    }

    override fun equals(other: Any?): Boolean {
        return (this === other && this.hashCode() == other.hashCode())
    }
}

data class HomeViewState(
    val refreshing: Boolean = true,
    val data: List<CommentEntity>? = null,
    val error: ArashniaException? = null
) : MviViewState {

    companion object {
        val init = HomeViewState(true)

        val reducer: Reducer<HomeViewState, HomeResult> = { state, result ->

            when (result) {
                HomeResult.Loading -> state.copy(refreshing = true)
                is HomeResult.Success -> {
                    state.copy(
                        refreshing = false,
                        error = null,
                        data = result.response
                    )
                }
                is HomeResult.Error -> state.copy(
                    refreshing = false,
                    error = result.error
                )
            }
        }
    }
}