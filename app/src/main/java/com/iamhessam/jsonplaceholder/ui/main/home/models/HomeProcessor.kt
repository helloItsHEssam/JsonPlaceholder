package com.iamhessam.jsonplaceholder.ui.main.home.models

import com.iamhessam.jsonplaceholder.data.Repository
import com.iamhessam.jsonplaceholder.ui.main.mvi.MviProcessor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class HomeProcessor : MviProcessor<HomeResult> {
    // TODO: - add Hilt Di For pass repository
    private val repository: Repository = Repository()

    object Refresh : HomeProcessor()

    override fun createResult(): Flow<HomeResult> = this.process()
    private fun process(): Flow<HomeResult> = when (this) {
        is Refresh -> {
            this.repository.getFakeData()
                .map {
                    HomeResult.Error(it)
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