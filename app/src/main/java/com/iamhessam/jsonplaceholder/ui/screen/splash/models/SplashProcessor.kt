package com.iamhessam.jsonplaceholder.ui.screen.splash.models

import com.iamhessam.jsonplaceholder.data.Repository
import com.iamhessam.jsonplaceholder.mvi.MviProcessor
import com.iamhessam.jsonplaceholder.mvi.MviProcessorType
import com.iamhessam.jsonplaceholder.utils.settings.theme.ActiveColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

sealed class SplashProcessorType : MviProcessorType {

    data class ChangeTheme(var activeColor: ActiveColor) : SplashProcessorType()
    object ReadingTheme : SplashProcessorType()
}

class SplashProcessor(override var processorType: SplashProcessorType) :
    MviProcessor<SplashResult, SplashProcessorType> {

    private lateinit var repository: Repository

    override fun injectRepository(repository: Repository) {
        this.repository = repository
    }

    override suspend fun mapToResult(): Flow<SplashResult> {
        return when (processorType) {
            is SplashProcessorType.ChangeTheme -> updateTheme((processorType as SplashProcessorType.ChangeTheme).activeColor)
            is SplashProcessorType.ReadingTheme -> readingTheme()
        }
    }

    private fun readingTheme(): Flow<SplashResult> {
        return this.repository.local.uiSettings.activeColor
            .map {
                SplashResult.Color(it)
            }
    }

    private suspend fun updateTheme(activeColor: ActiveColor): Flow<SplashResult> {
        return flow {
            repository.local.uiSettings.updateTheme(activeColor)
            emit(SplashResult.Success("OK"))
        }
    }
}