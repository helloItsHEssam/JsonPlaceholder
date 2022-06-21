package com.iamhessam.jsonplaceholder.utils.extension

import com.iamhessam.jsonplaceholder.ui.main.mvi.MviAction
import com.iamhessam.jsonplaceholder.ui.main.mvi.MviActionProcessor
import com.iamhessam.jsonplaceholder.ui.main.mvi.MviIntent
import com.iamhessam.jsonplaceholder.ui.main.mvi.MviResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<MviIntent<MviAction<MviResult, MviActionProcessor<MviResult>>>>.mapperIntentToAction(): Flow<MviAction<MviResult, MviActionProcessor<MviResult>>> =
    map { it.mapToAction() }

fun <R : MviResult,
        P : MviActionProcessor<R>,
        A : MviAction<R, P>>
        Flow<MviAction<MviResult, MviActionProcessor<MviResult>>>.mapperActionToResult(processor: MviActionProcessor<R>) =
    processor.apply()