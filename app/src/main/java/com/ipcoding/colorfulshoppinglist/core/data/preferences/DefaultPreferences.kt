package com.ipcoding.colorfulshoppinglist.core.data.preferences

import android.content.SharedPreferences
import com.ipcoding.colorfulshoppinglist.core.domain.preferences.Preferences

class DefaultPreferences(
    private val sharedPreferences: SharedPreferences
) : Preferences {

    override fun saveCurrentUrl(url: String?) {
        sharedPreferences
            .edit()
            .putString(Preferences.CURRENT_URL, url)
            .apply()
    }

    override fun saveCurrentId(id: Int) {
        sharedPreferences
            .edit()
            .putInt(Preferences.CURRENT_ID, id)
            .apply()
    }

    override fun saveCurrentText(text: String?) {
        sharedPreferences
            .edit()
            .putString(Preferences.CURRENT_TEXT, text)
            .apply()
    }

    override fun loadCurrentUrl(): String? {
        return sharedPreferences.getString(Preferences.CURRENT_URL, null)
    }

    override fun loadCurrentId(): Int {
        return sharedPreferences.getInt(Preferences.CURRENT_ID, -1)
    }

    override fun loadCurrentText(): String? {
        return sharedPreferences.getString(Preferences.CURRENT_TEXT, null)
    }
}