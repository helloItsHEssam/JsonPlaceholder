package com.iamhessam.jsonplaceholder.ui.main.home.models

import com.iamhessam.jsonplaceholder.ui.main.mvi.*

sealed class HomeResult : MviResult {
    object Loading : HomeResult()
    data class Error(val message: String) : HomeResult()
    data class Success(val response: String) : HomeResult()
}

sealed class HomeAction : MviAction<HomeResult, HomeProcessor> {
    object Refresh : HomeAction()
    object Init: HomeAction()
    object Cancel: HomeAction()
    data class LoadComment(val commentId: Int) : HomeAction()

    override fun mapToProcessor(): HomeProcessor = when (this) {
        is Refresh -> HomeProcessor.Refresh
        is LoadComment -> HomeProcessor.Init
        is Cancel -> HomeProcessor.Cancel
        is Init -> HomeProcessor.Init
    }
}

sealed class HomeIntent : MviIntent<HomeResult, HomeProcessor, HomeAction> {
    object Initial : HomeIntent()
    object PullToRefresh : HomeIntent()
    data class LoadComment(val commentId: Int) : HomeIntent()
    object Cancel: HomeIntent()

    override fun mapToAction(): HomeAction = when (this) {
        is Initial -> HomeAction.Init
        is PullToRefresh -> HomeAction.Refresh
        is Cancel -> HomeAction.Cancel
        is LoadComment -> HomeAction.LoadComment(this.commentId)
    }
}

data class HomeViewState(
    val refreshing: Boolean = true,
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