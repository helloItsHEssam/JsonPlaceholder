package com.iamhessam.jsonplaceholder.utils.extension

import com.iamhessam.jsonplaceholder.ui.main.mvi.MviAction
import com.iamhessam.jsonplaceholder.ui.main.mvi.MviIntent
import com.iamhessam.jsonplaceholder.ui.main.mvi.MviProcessor
import com.iamhessam.jsonplaceholder.ui.main.mvi.MviResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single

fun <R: MviResult, P: MviProcessor<R>, A: MviAction<R, P>, I: MviIntent<R, P, A>> Flow<I>.mapperIntentToAction(): Flow<A> = map { it.mapToAction() }

fun <R: MviResult, P: MviProcessor<R>, A: MviAction<R, P>> Flow<A>.mapperActionToProcessor(): Flow<P> = map { it.mapToProcessor() }

fun <R: MviResult, P: MviProcessor<R>> Flow<P>.mapperProcessorToResult(): Flow<R> = map { it.createResult().single() }