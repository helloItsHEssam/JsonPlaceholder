package com.iamhessam.jsonplaceholder.ui.main.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamhessam.jsonplaceholder.utils.extension.mapperActionToProcessor
import com.iamhessam.jsonplaceholder.utils.extension.mapperIntentToAction
import com.iamhessam.jsonplaceholder.utils.extension.mapperProcessorToResult
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.*

interface MviViewModel<R : MviResult, P : MviProcessor<R>, A : MviAction<R, P>, I : MviIntent<R, P, A>, S : MviViewState> {
    fun processorIntent(intent: I)
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

    init {
        this._intents
            .filterNotNull()
            .mapperIntentToAction()
            .mapperActionToProcessor()
            .mapperProcessorToResult()
            .scan(this.initialState, this.reducer)
            .distinctUntilChanged()
            .onEach(::changeSave)
            .launchIn(viewModelScope)
    }

    override fun processorIntent(intent: I) {
        this._intents.value = intent
    }

    fun states(): Flow<S> = this._states

    private fun changeSave(state: S) {
        this._states.value = state
    }
}