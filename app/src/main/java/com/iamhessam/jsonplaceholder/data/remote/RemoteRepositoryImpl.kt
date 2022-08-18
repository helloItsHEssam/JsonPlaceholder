package com.iamhessam.jsonplaceholder.data.remote

import com.iamhessam.jsonplaceholder.data.remote.http.ktor.engine.KtorClient
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    override var http: KtorClient,
) : RemoteRepository