package com.iamhessam.jsonplaceholder.utils.settings.theme

import androidx.datastore.preferences.core.stringPreferencesKey
import com.iamhessam.jsonplaceholder.data.local.datastore.preferences.PrefsStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class UISettingsImpl @Inject constructor(
    private val prefsStore: PrefsStore
) : UISettings {
    private val colorKey = stringPreferencesKey("color")

    override val shape: Shape
        get() = Shape()
    override val typography: Typography
        get() = Typography()

    override val activeColor: MutableStateFlow<ActiveColor>
    private var theme: ActiveColor by UISettingsDelegate()

    init {
        activeColor = MutableStateFlow(theme)
    }

    override fun updateTheme(activeColor: ActiveColor) {
        theme = activeColor
    }

    inner class UISettingsDelegate : ReadWriteProperty<Any?, ActiveColor> {

        override fun getValue(thisRef: Any?, property: KProperty<*>): ActiveColor =
            runBlocking {
                return@runBlocking prefsStore
                    .getPreference(colorKey, ActiveColor.System.convertToString)
                    .map {
                        ActiveColor.createFromString(it)
                    }.first()
            }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: ActiveColor) {
            activeColor.value = value
            runBlocking {
                prefsStore.putPreference(colorKey, value.convertToString)
            }
        }
    }
}