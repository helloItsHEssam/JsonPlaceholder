package com.iamhessam.jsonplaceholder.ui.main.home.models

import com.iamhessam.jsonplaceholder.data.Repository
import com.iamhessam.jsonplaceholder.ui.main.mvi.MviProcessor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

sealed class HomeProcessor : MviProcessor<HomeResult> {
    // TODO: - add Hilt Di For pass repository
    private val repository: Repository = Repository()

    object Refresh : HomeProcessor()
    object Init : HomeProcessor()

    override fun createResult(): Flow<HomeResult> = this.process()
    private fun process(): Flow<HomeResult> = when (this) {
        is Refresh -> refreshHandle()
        is Init -> initHandler()
    }

    private fun initHandler(): Flow<HomeResult> {
        return this.repository.getFakeData()
            .onStart { HomeResult.Loading }
            .map { HomeResult.Error(it) }
            .catch { e -> HomeResult.Error(e.message ?: "Error") }
            .flowOn(Dispatchers.IO)
    }

    private fun refreshHandle(): Flow<HomeResult> {
        return this.repository.getFakeData()
            .onStart { HomeResult.Loading }
            .map { HomeResult.Success(it) }
            .catch { e -> HomeResult.Error(e.message ?: "Error") }
            .flowOn(Dispatchers.IO)
    }
}