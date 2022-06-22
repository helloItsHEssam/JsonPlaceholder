package com.iamhessam.jsonplaceholder.ui.main.mvi

import kotlinx.coroutines.flow.Flow

interface MviIntent<R: MviResult, P: MviProcessor<R>,A : MviAction<R, P>> {
    fun mapToAction(): A
}

interface MviAction<R: MviResult, P: MviProcessor<R>> {
    fun mapToProcessor(): P
}

interface MviResult

interface MviViewState

interface MviProcessor<R : MviResult> {
    fun apply(): Flow<R> = this.getActionProcessors()
    fun getActionProcessors(): Flow<R>
}

typealias Reducer<S, R> = (state: S, result: R) -> S