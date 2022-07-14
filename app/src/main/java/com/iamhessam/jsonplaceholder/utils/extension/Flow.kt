package com.iamhessam.jsonplaceholder.utils.extension

import com.iamhessam.jsonplaceholder.mvi.MviAction
import com.iamhessam.jsonplaceholder.mvi.MviIntent
import com.iamhessam.jsonplaceholder.mvi.MviProcessor
import com.iamhessam.jsonplaceholder.mvi.MviResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <R: MviResult, P: MviProcessor<R>, A: MviAction<R, P>, I: MviIntent<R, P, A>> Flow<I>.mapperIntentToAction(): Flow<A> = map { it.mapToAction() }

fun <R: MviResult, P: MviProcessor<R>, A: MviAction<R, P>> Flow<A>.mapperActionToProcessor(): Flow<P> = map { it.mapToProcessor() }

fun <R: MviResult, P: MviProcessor<R>> Flow<P>.mapperProcessorToResult(): Flow<R> = map { it.mapToResult() }