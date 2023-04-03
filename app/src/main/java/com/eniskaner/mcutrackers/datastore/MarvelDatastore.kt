package com.eniskaner.mcutrackers.datastore

import android.content.Context
import android.support.annotation.MenuRes
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.eniskaner.mcutrackers.R
import com.eniskaner.mcutrackers.database.model.Phase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.datastore by preferencesDataStore("marvel_data_store")

class MarvelDatastore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getPhase(): Flow<Phase> {
        return context.datastore.data.map { preferences ->
            preferences[phaseKey] ?: R.id.filter_phase_all
        }.map { menuId ->
            mapMenuIdtoPhase(menuId) ?: Phase.ALL
        }
    }

    suspend fun setPhase(@MenuRes menuId: Int) {
        context.datastore.edit { preferences ->
            preferences[phaseKey] = menuId
        }
    }

    private fun mapMenuIdtoPhase(@MenuRes menuId: Int): Phase? {
        return when (menuId) {
            R.id.filter_phase_all -> Phase.ALL
            R.id.filter_phase_1 -> Phase.ONE
            R.id.filter_phase_2 -> Phase.TWO
            R.id.filter_phase_3 -> Phase.THREE
            R.id.filter_phase_4 -> Phase.FOUR
            else -> null
        }
    }

    companion object {
        private val phaseKey = intPreferencesKey("phase_key")
    }
}