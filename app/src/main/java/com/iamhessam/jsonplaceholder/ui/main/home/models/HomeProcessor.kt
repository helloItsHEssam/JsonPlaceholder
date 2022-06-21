package com.iamhessam.jsonplaceholder.ui.main.home.models

import com.iamhessam.jsonplaceholder.data.Repository
import com.iamhessam.jsonplaceholder.ui.main.mvi.MviActionProcessor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class HomeProcessor :
    MviActionProcessor<HomeResult>() {
    private val repository: Repository = Repository() // DI
    object Refresh : HomeProcessor()

    override fun getActionProcessors(): Flow<HomeResult> = this.process()
    private fun process(): Flow<HomeResult> = when (this) {
        is Refresh -> {
            this.repository.getFakeData()
                .map {
                    HomeResult.Success(it)
                }
                .onStart {
                    HomeResult.Loading
                }
                .catch { e ->
                    HomeResult.Error(e.message ?: "Error")
                }
        }
    }
}