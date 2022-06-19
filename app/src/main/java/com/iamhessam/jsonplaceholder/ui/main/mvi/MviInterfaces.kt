package com.iamhessam.jsonplaceholder.ui.main.mvi

interface MviIntent<out A : MviAction> {
    fun mapToAction(): A
}

interface MviAction

interface MviResult

interface MviViewState

typealias Reducer<S, R> = (state: S, result: R) -> S