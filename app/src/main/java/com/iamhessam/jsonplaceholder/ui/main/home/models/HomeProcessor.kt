package com.iamhessam.jsonplaceholder.ui.main.home.models

import com.iamhessam.jsonplaceholder.data.Repository
import com.iamhessam.jsonplaceholder.ui.main.mvi.MviProcessor
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

sealed class HomeProcessor : MviProcessor<HomeResult> {
    // TODO: - add Hilt Di For pass repository
    private val repository: Repository = Repository()

    object Refresh : HomeProcessor()
    object Init : HomeProcessor()
    object Cancel: HomeProcessor()


    var jobs: MutableSet<Flow<HomeResult>> = mutableSetOf()

    override fun createResult(): Flow<HomeResult> = this.process()
    private fun process(): Flow<HomeResult> = when (this) {
        is Refresh -> {
            val fl = refreshHandle()
            jobs.add(fl)
            fl
        }
        is Init -> {
            val fl = initHandler()
            jobs.add(fl)
            fl
        }
        is Cancel -> {

        }
    }

    private fun cancelHandler(): Flow<HomeResult> {
        return flow {
            throw CancellationException
        }
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