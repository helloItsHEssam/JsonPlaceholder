package com.iamhessam.jsonplaceholder.utils.constant

import com.iamhessam.jsonplaceholder.BuildConfig

object AppConfig {

    val baseUrl: String = when (BuildConfig.FLAVOR) {
        "Product" -> "jsonplaceholder.typicode.com"
        else -> ""
    }

    val userAgent: String = when (BuildConfig.FLAVOR) {
        "Product" -> "user agent for product"
        else -> "user agent for develop"
    }

}