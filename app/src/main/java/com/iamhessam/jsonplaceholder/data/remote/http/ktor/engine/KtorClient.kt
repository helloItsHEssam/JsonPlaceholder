package com.iamhessam.jsonplaceholder.data.remote.http.ktor.engine

import com.iamhessam.jsonplaceholder.BuildConfig
import com.iamhessam.jsonplaceholder.data.remote.http.ktor.plugin.KtorLogging
import com.iamhessam.jsonplaceholder.data.remote.http.ktor.plugin.ServerErrorHandler
import com.iamhessam.jsonplaceholder.utils.constant.AppConfig
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.resources.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class KtorClient {

    lateinit var httpClient: HttpClient
        private set

    init {
        configureHttpClient()
    }

    public fun setEngine(engine: HttpClientEngine) {
        httpClient = HttpClient(engine) {
            install(Resources)

            install(ContentNegotiation) {
                json()
            }

            install(UserAgent) {
                agent = AppConfig.userAgent
            }

            HttpResponseValidator {
                validateResponse {
                    if (it.status.value >= 300) {
                        // TODO: complete here after get error data from server! :-)
//                    val customErrorMessage: CommentDTO = exceptionResponse.body()
                        val handler = ServerErrorHandler()
                        throw handler(it.status.value, "Error")
                    }
                }
            }
        }
    }

    private fun configureHttpClient() {
        httpClient = HttpClient(CIO) {

            installDefaultRequest(this)
            installLogger(this)
            installUserAgent(this)
            installJsonResponse(this)
            configValidationResponse(this)
            install(Resources)

            engine {
                configEngine(this)
            }
        }
    }

    private fun configEngine(engine: CIOEngineConfig) {
        engine.threadsCount = 4
        engine.requestTimeout = 30_000
        engine.maxConnectionsCount = 1_000
    }

    private fun installLogger(client: HttpClientConfig<CIOEngineConfig>) {
        client.install(Logging) {
            logger = KtorLogging()
            level = LogLevel.ALL
        }
    }

    private fun installDefaultRequest(client: HttpClientConfig<CIOEngineConfig>) {
        client.defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = AppConfig.baseUrl
            }

            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }

    private fun installUserAgent(client: HttpClientConfig<CIOEngineConfig>) {
        client.install(UserAgent) {
            agent = AppConfig.userAgent
        }
    }

    private fun installJsonResponse(client: HttpClientConfig<CIOEngineConfig>) {
        client.install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    private fun configValidationResponse(client: HttpClientConfig<CIOEngineConfig>) {
        client.expectSuccess = true
        client.HttpResponseValidator {
            validateResponse {
                if (it.status.value >= 300) {
                    // TODO: complete here after get error data from server! :-)
//                    val customErrorMessage: CommentDTO = exceptionResponse.body()
                    val handler = ServerErrorHandler()
                    throw handler(it.status.value, "Error")
                }
            }
        }
    }
}