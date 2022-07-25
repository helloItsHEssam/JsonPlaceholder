package com.iamhessam.jsonplaceholder.data.local

import com.iamhessam.jsonplaceholder.data.local.db.room.AppDB
import com.iamhessam.jsonplaceholder.utils.settings.theme.UISettings
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    override var appDB: AppDB,
    override var uiSettings: UISettings
) : LocalRepository