package com.iamhessam.jsonplaceholder.utils.extension

import com.iamhessam.jsonplaceholder.data.Repository
import com.iamhessam.jsonplaceholder.mvi.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map

fun <R : MviResult, T : MviProcessorType, P : MviProcessor<R, T>,
    A : MviAction<R, T, P>,
    I : MviIntent<R, T, P, A>> Flow<I>.mapperIntentToAction(): Flow<A> =
    map { it.mapToAction() }

fun <R : MviResult, T : MviProcessorType, P : MviProcessor<R, T>,
    A : MviAction<R, T, P>> Flow<A>.mapperActionToProcessor(): Flow<P> =
    map { it.mapToProcessor() }

@OptIn(FlowPreview::class)
fun <R : MviResult, T : MviProcessorType,
    P : MviProcessor<R, T>> Flow<P>.mapperProcessorToResult(
    repository: Repository
): Flow<R> = flatMapConcat {
    it.injectRepository(repository)
    it.mapToResult()
}