package com.iamhessam.jsonplaceholder.data

import com.iamhessam.jsonplaceholder.data.local.LocalRepository
import com.iamhessam.jsonplaceholder.data.remote.RemoteRepository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    override val local: LocalRepository,
    override val remote: RemoteRepository
) : Repository