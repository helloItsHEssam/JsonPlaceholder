package com.iamhessam.jsonplaceholder.ui.main.home.models

import com.iamhessam.jsonplaceholder.ui.main.mvi.*

sealed class HomeAction : HomeAct {
    object Refresh : HomeAction()
    data class LoadComment(val commentId: Int) : HomeAction()

    override fun mapToProcessor(): HomeProcessor = when (this) {
        is Refresh -> HomeProcessor.Refresh
        is LoadComment -> HomeProcessor.Refresh
    }
}

typealias HomeAct = MviAction<HomeResult, HomeProcessor>

sealed class HomeIntent : MviIntent<HomeAct> {
    object Initial : HomeIntent()
    object PullToRefresh : HomeIntent()
    data class LoadComment(val commentId: Int) : HomeIntent()

    override fun mapToAction(): HomeAction = when (this) {
        is Initial, PullToRefresh -> HomeAction.Refresh
        is LoadComment -> HomeAction.LoadComment(this.commentId)
    }
}

sealed class HomeResult : MviResult {
    object Loading : HomeResult()
    data class Error(val message: String) : HomeResult()
    data class Success(val response: String) : HomeResult()
}

data class HomeViewState(
    val refreshing: Boolean = false,
    val data: List<String>? = null,
    val error: String? = null
) : MviViewState {

    companion object {
        val reducer: Reducer<HomeViewState, HomeResult> = { state, result ->
            when (result) {
                HomeResult.Loading -> state.copy(refreshing = true)
                is HomeResult.Success -> {
                    state.copy(
                        refreshing = false,
                        error = null,
                        data = listOf(result.response)
                    )
                }
                is HomeResult.Error -> state.copy(
                    refreshing = false,
                    error = result.message
                )
            }
        }
    }
}