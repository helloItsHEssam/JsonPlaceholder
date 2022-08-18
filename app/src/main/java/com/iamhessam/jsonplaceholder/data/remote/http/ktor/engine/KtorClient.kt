package com.iamhessam.jsonplaceholder.data.remote.http.ktor.engine

import com.iamhessam.jsonplaceholder.data.remote.http.ktor.plugin.KtorLogging
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.logging.*

class KtorClient {

    lateinit var httpClient: HttpClient
        private set

    init {
        configureHttpClient()
    }

    private fun configureHttpClient() {

        httpClient = HttpClient(CIO) {

            // logging
            installLogger(this)

            // engine
            engine {
                configEngine(this)
            }
        }
    }

    private fun installLogger(client: HttpClientConfig<CIOEngineConfig>) {
        client.install(Logging) {
            logger = KtorLogging()
            level = LogLevel.ALL
        }
    }

    private fun configEngine(engine: CIOEngineConfig) {
        engine.threadsCount = 4
        engine.requestTimeout = 30_000
        engine.maxConnectionsCount = 1_000
    }
}