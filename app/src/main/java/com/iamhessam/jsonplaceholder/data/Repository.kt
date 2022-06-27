package com.iamhessam.jsonplaceholder.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository() {

    fun getFakeData(): Flow<String> {
        return flow {
//            delay(8_000)
            emit("Hello Hessam")
        }
    }

    suspend fun getFakeData2(): String {
        delay(5_000)
        return "Hello Hessam2"
    }
}