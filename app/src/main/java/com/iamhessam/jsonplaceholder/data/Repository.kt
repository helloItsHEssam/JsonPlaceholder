package com.iamhessam.jsonplaceholder.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository() {

    fun getFakeData(): Flow<String> {
        return flow {
            emit("Hello Hessam")
        }
    }
}