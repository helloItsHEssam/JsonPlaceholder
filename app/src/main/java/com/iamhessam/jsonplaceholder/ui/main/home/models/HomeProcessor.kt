package com.iamhessam.jsonplaceholder.ui.main.home.models

import android.util.Log
import com.iamhessam.jsonplaceholder.data.Repository
import com.iamhessam.jsonplaceholder.ui.main.mvi.MviProcessor
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.internal.ChannelFlow
import java.lang.Exception
import kotlin.properties.Delegates

sealed class HomeProcessor : MviProcessor<HomeResult> {
    // TODO: - add Hilt Di For pass repository
    private val repository: Repository = Repository()

    companion object {
        var jobs: MutableMap<Int, Job> by Delegates.observable(mutableMapOf()) { prop, old, new ->
            Log.d("Change JOOOB   ", new.size.toString())
        }
    }

    object Refresh : HomeProcessor()
    object Init : HomeProcessor()
    object Cancel : HomeProcessor()

    override fun createResult(): Flow<HomeResult> = this.process()
    private fun process(): Flow<HomeResult> = when (this) {
        is Refresh -> {
            initHandler()
//            runBlocking {
//                val job = async(Dispatchers.IO) {
//                   "hessssam"
//                }
//
//                job.cancel(CancellationException("Error Cancel"))
//                flowOf(HomeResult.Error(job.await()))
//            }
        }

        is Init -> {
            refreshHandle()
        }

        is Cancel -> {
            refreshHandle()
//            val r = runBlocking {
//                val targetJob = jobs[100]
//                targetJob?.cancel()
//                flowOf(HomeResult.Error("hi"))
//            }
//            r
        }
    }

    private fun initHandler(): Flow<HomeResult> {
        return this.repository.getFakeData()
            .onStart { HomeResult.Loading }
            .map { HomeResult.Error(it) }
            .catch { e -> HomeResult.Error(e.message ?: "Error") }
            .cancellable()
            .flowOn(Dispatchers.IO)

    }

    private fun refreshHandle(): Flow<HomeResult> {
        return this.repository.getFakeData()
            .onStart { HomeResult.Loading }
            .map { HomeResult.Success(it) }
            .catch { e -> HomeResult.Error(e.message ?: "Error") }
            .cancellable()
            .flowOn(Dispatchers.IO)
    }
}