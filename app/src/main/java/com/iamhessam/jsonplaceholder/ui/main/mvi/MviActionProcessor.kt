package com.iamhessam.jsonplaceholder.ui.main.mvi

import kotlinx.coroutines.flow.Flow

abstract class MviActionProcessor<R : MviResult> {

    fun apply(): Flow<R> = this.getActionProcessors()

    abstract fun getActionProcessors(): Flow<R>
}