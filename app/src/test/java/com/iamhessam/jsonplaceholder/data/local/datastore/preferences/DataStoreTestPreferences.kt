package com.iamhessam.jsonplaceholder.data.local.datastore.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DataStoreTestPreferences {

    private val key = stringPreferencesKey("color")
    private lateinit var ds: PrefsStoreImpl
    private val testContext: Context = ApplicationProvider.getApplicationContext()
    private val testDataStore: DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile =
            { testContext.preferencesDataStoreFile("test_file") }
        )

    @Before
    fun setup() {
        ds = PrefsStoreImpl(testDataStore)
    }

    @After
    fun cleanUp() {
        runBlocking {
            testDataStore.edit { it.clear() }
        }
    }

    @Test
    fun insertTest() {
        runBlocking {
            ds.putPreference(key, "hello")
            val result = ds.getPreference(key, "hi")
            Assert.assertEquals("hello", result.first())
        }
    }
}