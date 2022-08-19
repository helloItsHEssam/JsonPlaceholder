package com.iamhessam.jsonplaceholder.data

import com.iamhessam.jsonplaceholder.data.local.LocalRepository
import com.iamhessam.jsonplaceholder.data.remote.RemoteRepository

interface Repository {
    val local: LocalRepository
    val remote: RemoteRepository
}