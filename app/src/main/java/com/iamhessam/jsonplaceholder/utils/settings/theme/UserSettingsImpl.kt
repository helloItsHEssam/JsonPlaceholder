package com.iamhessam.jsonplaceholder.utils.settings.theme

import androidx.datastore.preferences.core.stringPreferencesKey
import com.iamhessam.jsonplaceholder.data.local.datastore.preferences.PrefsStore
import javax.inject.Inject

class UserSettingsImpl @Inject constructor(private val prefsStore: PrefsStore) : UserSettings {

    private val color = stringPreferencesKey("color")

}