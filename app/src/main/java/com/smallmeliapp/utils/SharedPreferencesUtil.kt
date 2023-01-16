package com.smallmeliapp.utils

import android.content.SharedPreferences
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesUtil @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun setSharedPreferences(key: String, value: Int) =
        sharedPreferences.edit().putInt(key, value).apply()

    fun setSharedPreferences(key: String, value: String) =
        sharedPreferences.edit().putString(key, value).apply()

    fun setSharedPreferences(key: String, value: Boolean) =
        sharedPreferences.edit().putBoolean(key, value).apply()

    fun setSharedPreferences(key: String, value: Long) =
        sharedPreferences.edit().putLong(key, value).apply()

    fun setSharedPreferences(key: String, value: Float) =
        sharedPreferences.edit().putFloat(key, value).apply()

    fun getSharedPreferenceString(key: String) = sharedPreferences.getString(key, "")

    fun getSharedPreferenceInt(key: String) = sharedPreferences.getInt(key, 0)

    fun getSharedPreferenceBoolean(key: String) = sharedPreferences.getBoolean(key, false)

    fun getSharedPreferenceLong(key: String) = sharedPreferences.getLong(key, 0)

    fun getSharedPreferenceFloat(key: String) = sharedPreferences.getFloat(key, 0F)


}