package com.iamhessam.jsonplaceholder.ui.main.home.models

import com.iamhessam.jsonplaceholder.ui.main.mvi.MviAction
import com.iamhessam.jsonplaceholder.ui.main.mvi.MviIntent
import com.iamhessam.jsonplaceholder.ui.main.mvi.MviResult

sealed class HomeAction : MviAction {
    object Refresh : HomeAction()
    data class LoadComment(val commentId: Int) : HomeAction()
}

sealed class HomeIntent : MviIntent<HomeAction> {

    object Initial : HomeIntent()
    object PullToRefresh : HomeIntent()
    data class LoadComment(val commentId: Int) : HomeIntent()

    override fun mapToAction(): HomeAction = when (this) {
        is Initial, PullToRefresh -> HomeAction.Refresh
        is LoadComment -> HomeAction.LoadComment(this.commentId)
    }
}

sealed class HomeResult: MviResult {

    object Loading: HomeResult()
    data class Error(val message: String): HomeResult()
    data class Success(val response: String): HomeResult()
}