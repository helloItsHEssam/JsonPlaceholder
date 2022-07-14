package com.iamhessam.jsonplaceholder.mvi

interface MviResult

interface MviViewState

interface MviIntent<out R : MviResult, out P : MviProcessor<R>, out A : MviAction<R, P>> {
    fun mapToAction(): A
    override fun hashCode(): Int
    val uniqueId: Int
        get() = hashCode()
}

interface MviAction<out R : MviResult, out P : MviProcessor<R>> {
    fun mapToProcessor(): P
}

abstract class MviProcessor<out R : MviResult> {
    abstract suspend fun mapToResult(): R
}

typealias Reducer<S, R> = (state: S, result: R) -> S