package com.iamhessam.jsonplaceholder.ui.main.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamhessam.jsonplaceholder.utils.extension.mapperActionToProcessor
import com.iamhessam.jsonplaceholder.utils.extension.mapperIntentToAction
import com.iamhessam.jsonplaceholder.utils.extension.mapperProcessorToResult
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface MviViewModel<R : MviResult, P : MviProcessor<R>, A : MviAction<R, P>, I : MviIntent<R, P, A>, S : MviViewState> {
    fun processorIntent(intent: I)
    fun cancelIntent(intent: I)
}

interface MviView<R : MviResult, P : MviProcessor<R>, A : MviAction<R, P>, I : MviIntent<R, P, A>, S : MviViewState> {
    fun render(state: S)
}

open class BaseViewModel<R : MviResult, P : MviProcessor<R>, A : MviAction<R, P>, I : MviIntent<R, P, A>, S : MviViewState>(
    private val initialState: S,
    initialIntent: I,
    private val reducer: Reducer<S, R>
) : MviViewModel<R, P, A, I, S>, ViewModel() {

    private val _intents: MutableStateFlow<I> = MutableStateFlow(initialIntent)
    private val _states: MutableStateFlow<S> = MutableStateFlow(this.initialState)
    private val jobs: MutableMap<Int, Job> = mutableMapOf()

//    init {
//        this._intents
//            .filterNotNull()
//            .mapperIntentToAction()
//            .mapperActionToProcessor()
//            .mapperProcessorToResult()
//            .scan(this.initialState, this.reducer)
//            .distinctUntilChanged()
//            .onEach(::changeSave)
//            .launchIn(viewModelScope)
//    }

    override fun processorIntent(intent: I) {
//        this._intents.value = intent
        val job = this.createJob(intent)
        jobs[intent.uniqueId] = job
    }

    override fun cancelIntent(intent: I) {
        viewModelScope.launch {
            val targetJob = this@BaseViewModel.jobs[intent.uniqueId]
            if (targetJob?.isActive == true) {
                targetJob.cancel(CancellationException("Cancel"))
                targetJob.join()
            }
        }
    }

    fun states(): Flow<S> = this._states

    private fun createJob(intent: I): Job {
        return MutableStateFlow(intent)
            .filterNotNull()
            .mapperIntentToAction()
            .mapperActionToProcessor()
            .mapperProcessorToResult()
            .scan(this.initialState, this.reducer)
            .distinctUntilChanged()
            .onEach(::changeSave)
            .launchIn(viewModelScope)
    }

    private fun changeSave(state: S) {
        this._states.value = state
    }
}