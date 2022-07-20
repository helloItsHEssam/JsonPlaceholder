package com.iamhessam.jsonplaceholder.utils.settings.theme

import androidx.datastore.preferences.core.stringPreferencesKey
import com.iamhessam.jsonplaceholder.data.local.datastore.preferences.PrefsStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserSettingsImpl @Inject constructor(
    private val prefsStore: PrefsStore
) : UserSettings {
    private val colorKey = stringPreferencesKey("color")

    override val shape: Shape
        get() = Shape()
    override val typography: Typography
        get() = Typography()

    override val activeColor: StateFlow<ActiveColor>

    private suspend fun readingActiveColor(): Flow<ActiveColor> {
        return prefsStore
            .getPreference(colorKey, ActiveColor.System.toString())
            .map {
                ActiveColor.createFromString(it)
            }
    }
}