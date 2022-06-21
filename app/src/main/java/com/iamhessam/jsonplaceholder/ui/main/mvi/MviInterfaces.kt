package com.iamhessam.jsonplaceholder.ui.main.mvi

interface MviIntent<A : MviAction<MviResult, MviActionProcessor<MviResult>>> {
    fun mapToAction(): A
}

interface MviAction<R: MviResult, out P: MviActionProcessor<R>> {
    fun mapToProcessor(): P
}

interface MviResult

interface MviViewState

typealias Reducer<S, R> = (state: S, result: R) -> S