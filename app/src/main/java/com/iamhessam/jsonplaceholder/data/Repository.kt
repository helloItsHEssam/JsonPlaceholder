package com.iamhessam.jsonplaceholder.data

import com.iamhessam.jsonplaceholder.data.local.LocalRepository

interface Repository {

    val local: LocalRepository

    // http
}