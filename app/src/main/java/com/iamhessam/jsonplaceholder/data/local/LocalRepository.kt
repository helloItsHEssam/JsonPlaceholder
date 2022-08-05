package com.iamhessam.jsonplaceholder.data.local

import com.iamhessam.jsonplaceholder.data.local.db.room.AppDB
import com.iamhessam.jsonplaceholder.utils.settings.theme.UISettings

interface LocalRepository {
    var appDB: AppDB
    var uiSettings: UISettings
}