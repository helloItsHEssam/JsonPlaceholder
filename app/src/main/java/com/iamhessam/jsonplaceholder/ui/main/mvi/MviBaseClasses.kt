package com.iamhessam.jsonplaceholder.ui.main.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamhessam.jsonplaceholder.utils.extension.mapperActionToResult
import com.iamhessam.jsonplaceholder.utils.extension.mapperIntentToAction
import kotlinx.coroutines.flow.*

interface MviViewModel<in I : MviIntent<A>, A : MviAction, out S : MviViewState> {
    fun processorIntent(intent: I)
}

interface MviView<I : MviIntent<A>, A : MviAction, S : MviViewState> {
    val intents: Flow<I>
}

open class BaseViewModel<I : MviIntent<A>, A : MviAction, R : MviResult, S : MviViewState>(
    private val initialState: S,
    private val reducer: Reducer<S, R>,
    private val processor: MviActionProcessor<A, R>
) : MviViewModel<I, A, S>, ViewModel() {

    private val _intents = MutableStateFlow<I?>(null)
    private val _states = MutableStateFlow(this.initialState)

    init {
        this._intents
            .filterNotNull()
            .mapperIntentToAction()
            .mapperActionToResult(this.processor)
            .scan(this.initialState, this.reducer)
            .distinctUntilChanged()
            .launchIn(viewModelScope)
    }

    override fun processorIntent(intent: I) {
        this._intents.value = intent
    }

    fun states(): Flow<S> = this._states
}