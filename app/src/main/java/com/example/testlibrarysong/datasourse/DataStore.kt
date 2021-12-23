package com.example.testlibrarysong.datasourse

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStore(context: Context) {

    private val dataStore = context.dataStore

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

        private val IS_FIRST_LAUNCH = booleanPreferencesKey("is first launch")
    }

    fun getFirstLaunch(): Flow<Boolean> {
        return dataStore.data.map {
            it[IS_FIRST_LAUNCH] ?: false
        }
    }

    suspend fun setFirstLaunch() {
        dataStore.edit {
            it[IS_FIRST_LAUNCH] = true
        }
    }
}