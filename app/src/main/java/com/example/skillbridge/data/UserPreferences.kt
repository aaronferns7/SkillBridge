package com.example.skillbridge.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.skillBridgeDataStore by preferencesDataStore("skillbridge_preferences")

/** Small persistence boundary for onboarding and future auth/session preferences. */
class UserPreferences(private val context: Context) {
    private val onboardingKey = booleanPreferencesKey("onboarding_complete")
    val onboardingComplete: Flow<Boolean> = context.skillBridgeDataStore.data.map { it[onboardingKey] ?: false }
    suspend fun setOnboardingComplete() { context.skillBridgeDataStore.edit { it[onboardingKey] = true } }
}
