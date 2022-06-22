package com.iamhessam.jsonplaceholder.utils.extension

import com.iamhessam.jsonplaceholder.ui.main.mvi.MviAction
import com.iamhessam.jsonplaceholder.ui.main.mvi.MviProcessor
import com.iamhessam.jsonplaceholder.ui.main.mvi.MviIntent
import com.iamhessam.jsonplaceholder.ui.main.mvi.MviResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<MviIntent<MviAction<MviResult, MviProcessor<MviResult>>>>.mapperIntentToAction(): Flow<MviAction<MviResult, MviProcessor<MviResult>>> =
    map { it.mapToAction() }

fun <R : MviResult,
        P : MviProcessor<R>,
        A : MviAction<R, P>>
        Flow<MviAction<MviResult, MviProcessor<MviResult>>>.mapperActionToResult(processor: MviProcessor<R>) =
    processor.apply()