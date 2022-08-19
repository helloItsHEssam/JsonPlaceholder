package com.iamhessam.jsonplaceholder.data.remote

import com.iamhessam.jsonplaceholder.data.remote.http.ktor.engine.KtorClient

interface RemoteRepository {
    var http: KtorClient
}