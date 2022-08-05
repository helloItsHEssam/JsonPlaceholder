package com.iamhessam.jsonplaceholder.data

import com.iamhessam.jsonplaceholder.data.local.LocalRepository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(override val local: LocalRepository) : Repository