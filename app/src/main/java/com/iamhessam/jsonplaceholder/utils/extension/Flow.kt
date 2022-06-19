package com.iamhessam.jsonplaceholder.utils.extension

import com.iamhessam.jsonplaceholder.ui.main.mvi.MviAction
import com.iamhessam.jsonplaceholder.ui.main.mvi.MviActionProcessor
import com.iamhessam.jsonplaceholder.ui.main.mvi.MviIntent
import com.iamhessam.jsonplaceholder.ui.main.mvi.MviResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<MviIntent<MviAction>>.mapperIntentToAction(): Flow<MviAction> = map { it.mapToAction() }

fun <A : MviAction, R : MviResult> Flow<MviAction>.mapperActionToResult(processor: MviActionProcessor<A, R>) =
    processor.apply()