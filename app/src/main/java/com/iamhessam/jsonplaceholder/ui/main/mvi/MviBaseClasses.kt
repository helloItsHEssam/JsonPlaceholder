package com.iamhessam.jsonplaceholder.ui.main.mvi

import android.util.Log
import android.util.Log.i
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.util.logging.Logger

interface MviViewModel<in I : MviIntent<A>, A : MviAction, out S : MviViewState> {

    val states: Flow<S>
    fun processorIntents()
}

interface MviView<I : MviIntent<A>, A : MviAction, S : MviViewState> {
    val intents: Flow<I>
}

open class BaseViewModel<I : MviIntent<A>, A : MviAction, R : MviResult, S : MviViewState>(
    private val initialIntent: Class<out I>,
    initialState: S,
    private val reducer: Reducer<S, R>,
) : MviViewModel<I, A, S>, ViewModel() {

    private val intentFilter = Channel<I>(Channel.UNLIMITED)
    private val _state = MutableStateFlow(initialState)

    override val states: Flow<S> = this._state

    override fun processorIntents() {
        viewModelScope.launch {
            this@BaseViewModel.intentFilter
                .consumeAsFlow()
                .collect {
                    _state = reducer(it.mapToAction())
            }
        }
    }

    private fun internalLogger(state: S) =  Log.i("newState", "$state")

    private fun crashHandler(throwable: Throwable): Unit = throw throwable
}