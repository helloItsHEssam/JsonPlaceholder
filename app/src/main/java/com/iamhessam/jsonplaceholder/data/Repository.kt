package com.iamhessam.jsonplaceholder.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.isActive

class Repository() {

    fun getFakeData(): Flow<String> {
        return flow {
            while (true) {
                emit("Hello Hessam")
            }
        }
    }

    suspend fun getFakeData2(): String = "Hello Hessam2"
}