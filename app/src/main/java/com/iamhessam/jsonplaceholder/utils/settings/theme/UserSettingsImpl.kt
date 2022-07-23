package com.iamhessam.jsonplaceholder.utils.settings.theme

import androidx.datastore.preferences.core.stringPreferencesKey
import com.iamhessam.jsonplaceholder.data.local.datastore.preferences.PrefsStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class UserSettingsImpl @Inject constructor(
    private val prefsStore: PrefsStore
) : UserSettings {
    private val colorKey = stringPreferencesKey("color")

    override val shape: Shape
        get() = Shape()
    override val typography: Typography
        get() = Typography()

    override val activeColor: Flow<ActiveColor> = readingActiveColor()

    private fun readingActiveColor() =
        runBlocking {
            return@runBlocking prefsStore
                .getPreference(colorKey, ActiveColor.System.toString())
                .map {
                    ActiveColor.createFromString(it)
                }
        }
}