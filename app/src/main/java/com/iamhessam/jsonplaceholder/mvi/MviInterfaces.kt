package com.iamhessam.jsonplaceholder.mvi

import com.iamhessam.jsonplaceholder.data.Repository
import kotlinx.coroutines.flow.Flow

interface MviResult

interface MviViewState

interface MviIntent<out R : MviResult, T : MviProcessorType,
    out P : MviProcessor<R, T>, out A : MviAction<R, T, P>> {
    fun mapToAction(): A
    override fun hashCode(): Int
    val uniqueId: Int
        get() = hashCode()
}

interface MviAction<out R : MviResult, T : MviProcessorType, out P : MviProcessor<R, T>> {
    fun mapToProcessor(): P
}

interface MviProcessor<out R : MviResult, T : MviProcessorType> {
    suspend fun mapToResult(): Flow<R>
    fun injectRepository(repository: Repository)
    var processorType: T
}

interface MviProcessorType

typealias Reducer<S, R> = (state: S, result: R) -> S