package com.iamhessam.jsonplaceholder.ui.screen.splash.models

import com.iamhessam.jsonplaceholder.mvi.*
import com.iamhessam.jsonplaceholder.utils.settings.theme.ActiveColor

sealed class SplashResult : MviResult {
    data class Success(val response: String) : SplashResult()
    data class Color(val activeColor: ActiveColor) : SplashResult()
}

sealed class SplashAction : MviAction<SplashResult, SplashProcessorType, SplashProcessor> {
    object ReadingTheme : SplashAction()
    data class UpdateTheme(val activeColor: ActiveColor) : SplashAction()

    override fun mapToProcessor(): SplashProcessor = when (this) {
        is ReadingTheme -> SplashProcessor(SplashProcessorType.ReadingTheme)
        is UpdateTheme -> SplashProcessor(SplashProcessorType.ChangeTheme(this.activeColor))
    }
}

sealed class SplashIntent : MviIntent<SplashResult, SplashProcessorType, SplashProcessor, SplashAction> {
    object ReadingTheme : SplashIntent()
    data class UpdateTheme(val activeColor: ActiveColor) : SplashIntent()

    override fun hashCode(): Int = when (this) {
        is ReadingTheme -> 1
        is UpdateTheme -> 2
    }

    override fun mapToAction(): SplashAction = when (this) {
        is ReadingTheme -> SplashAction.ReadingTheme
        is UpdateTheme -> SplashAction.UpdateTheme(this.activeColor)
    }

    override fun equals(other: Any?): Boolean {
        return (this === other && this.hashCode() == other.hashCode())
    }
}

data class SplashViewState(
    val data: String? = null,
    val color: ActiveColor? = null
) : MviViewState {

    companion object {
        val init = SplashViewState()

        val reducer: Reducer<SplashViewState, SplashResult> = { state, result ->
            when (result) {
                is SplashResult.Success -> state.copy(data = result.toString(), color = null)
                is SplashResult.Color -> state.copy(data = null, color = result.activeColor)
            }
        }
    }
}