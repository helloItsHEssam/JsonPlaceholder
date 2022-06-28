package com.iamhessam.jsonplaceholder.ui.main.mvi

interface MviResult

interface MviViewState

interface MviIntent<out R: MviResult, out P: MviProcessor<R>,out A : MviAction<R, P>> {
    fun mapToAction(): A
    override fun hashCode(): Int
    val uniqueId: Int
        get() = hashCode()
}

interface MviAction<out R: MviResult, out P: MviProcessor<R>> {
    fun mapToProcessor(): P
}

interface MviProcessor<out R : MviResult> {
    suspend fun mapToResult(): R
}

typealias Reducer<S, R> = (state: S, result: R) -> S