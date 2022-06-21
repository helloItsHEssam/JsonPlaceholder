package com.iamhessam.jsonplaceholder.ui.main.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamhessam.jsonplaceholder.utils.extension.mapperActionToResult
import com.iamhessam.jsonplaceholder.utils.extension.mapperIntentToAction
import kotlinx.coroutines.flow.*

interface MviViewModel<I : MviIntent<MviAction<MviResult, MviActionProcessor<MviResult>>>,
        R : MviResult,
        P : MviActionProcessor<R>,
        A : MviAction<R, P>,
        out S : MviViewState> {
    fun processorIntent(intent: I)
}

interface MviView<I : MviIntent<MviAction<MviResult, MviActionProcessor<MviResult>>>,
        R : MviResult,
        P : MviActionProcessor<R>,
        A : MviAction<R, P>,
        S : MviViewState> {
    val intents: Flow<I>
}

open class BaseViewModel<I : MviIntent<MviAction<MviResult, MviActionProcessor<MviResult>>>,
        R : MviResult,
        P : MviActionProcessor<R>,
        A : MviAction<R, P>,
        S : MviViewState>(
    private val initialState: S,
    private val reducer: Reducer<S, R>
) : MviViewModel<I, R, P, A, S>, ViewModel() {

    private val _intents = MutableStateFlow<I?>(null)
    private val _states = MutableStateFlow(this.initialState)

    init {
//        val r = this._intents
//            .filterNotNull()
//            .mapperIntentToAction()
//
//                r
//
//
//            .mapperActionToResult(this.processor)
//            .scan(this.initialState, this.reducer)
//            .distinctUntilChanged()
//            .launchIn(viewModelScope)

    }

    override fun processorIntent(intent: I) {
        this._intents.value = intent
    }

    fun states(): Flow<S> = this._states
}