package com.iamhessam.jsonplaceholder.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamhessam.jsonplaceholder.data.Repository
import com.iamhessam.jsonplaceholder.utils.extension.mapperActionToProcessor
import com.iamhessam.jsonplaceholder.utils.extension.mapperIntentToAction
import com.iamhessam.jsonplaceholder.utils.extension.mapperProcessorToResult
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface MviViewModel<R : MviResult, T : MviProcessorType, P : MviProcessor<R, T>, A : MviAction<R, T, P>, I : MviIntent<R, T, P, A>, S : MviViewState> {
    fun processorIntent(intent: I)
    fun cancelIntent(intent: I)
}

open class BaseViewModel<R : MviResult, T : MviProcessorType, P : MviProcessor<R, T>, A : MviAction<R, T, P>, I : MviIntent<R, T, P, A>, S : MviViewState>(
    private val initialState: S,
    private val initialIntent: I?,
    private val reducer: Reducer<S, R>,
    private val repository: Repository
) : MviViewModel<R, T, P, A, I, S>, ViewModel() {

    private val _states: MutableStateFlow<S> = MutableStateFlow(this.initialState)
    private val jobs: MutableMap<Int, Job> = mutableMapOf()

    init {
        // check null initial State
        val checkExistIntent = this.checkExistIntentInQueueRunning(this.initialIntent)
        if (checkExistIntent) {
            this.processorIntent(this.initialIntent!!)
        }
    }

    override fun onCleared() {
        super.onCleared()

        this.cancelAllJobs()
    }

    override fun processorIntent(intent: I) {
        val job = this.createJob(intent)
        jobs[intent.uniqueId] = job
    }

    override fun cancelIntent(intent: I) {
        viewModelScope.launch {
            val targetJob = this@BaseViewModel.jobs[intent.uniqueId]
            this@BaseViewModel.cancelJob(targetJob)
        }
    }

    fun states(): Flow<S> = this._states

    private fun createJob(intent: I): Job {
        return MutableStateFlow(intent)
            .filterNotNull()
            .mapperIntentToAction()
            .mapperActionToProcessor()
            .mapperProcessorToResult(this.repository)
            .scan(this.initialState, this.reducer)
            .distinctUntilChanged()
            .onEach(::changeSave)
            .onCompletion {
                this@BaseViewModel.removeJobIntentFromTaskQueue(intent)
            }
            .launchIn(viewModelScope)
    }

    private fun checkExistIntentInQueueRunning(intent: I?): Boolean {
        intent?.let {
            if (this.jobs.containsKey(it.uniqueId)) return true
            return false
        }

        return false
    }

    private fun removeJobIntentFromTaskQueue(intent: I) {
        this.jobs.remove(intent.uniqueId)
    }

    private fun changeSave(state: S) {
        this._states.value = state
    }

    private suspend fun cancelJob(job: Job?) {
        if (job?.isActive == true) {
            job.cancel(CancellationException("Cancel"))
            job.join()
        }
    }

    private fun cancelAllJobs() {
        viewModelScope.launch {
            this@BaseViewModel.jobs.forEach {
                this@BaseViewModel.cancelJob(it.value)
            }
        }
    }
}