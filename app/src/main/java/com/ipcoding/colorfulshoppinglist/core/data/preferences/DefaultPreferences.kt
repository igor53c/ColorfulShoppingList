package com.ipcoding.colorfulshoppinglist.core.data.preferences

import android.content.SharedPreferences
import com.ipcoding.colorfulshoppinglist.core.domain.preferences.Preferences
import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item

class DefaultPreferences(
    private val sharedPreferences: SharedPreferences
) : Preferences {

    override fun saveCurrentUrl(url: String?) {
        sharedPreferences
            .edit()
            .putString(Preferences.CURRENT_URL, url)
            .apply()
    }

    override fun saveCurrentText(text: String?) {
        sharedPreferences
            .edit()
            .putString(Preferences.CURRENT_TEXT, text)
            .apply()
    }

    override fun saveCurrentIsMarked(isMarked: Boolean) {
        sharedPreferences
            .edit()
            .putBoolean(Preferences.CURRENT_IS_MARKED, isMarked)
            .apply()
    }

    override fun saveCurrentId(id: Int) {
        sharedPreferences
            .edit()
            .putInt(Preferences.CURRENT_ID, id)
            .apply()
    }

    override fun loadCurrentUrl(): String? {
        return sharedPreferences.getString(Preferences.CURRENT_URL, null)
    }

    override fun loadCurrentText(): String? {
        return sharedPreferences.getString(Preferences.CURRENT_TEXT, null)
    }

    override fun loadCurrentIsMarked(): Boolean {
        return sharedPreferences.getBoolean(Preferences.CURRENT_IS_MARKED, false)
    }

    override fun loadCurrentId(): Int {
        return sharedPreferences.getInt(Preferences.CURRENT_ID, -1)
    }

    override fun saveIsImageDisplayed(isImageDisplayed: Boolean) {
        sharedPreferences
            .edit()
            .putBoolean(Preferences.IS_IMAGE_DISPLAYED, isImageDisplayed)
            .apply()
    }

    override fun loadIsImageDisplayed(): Boolean {
        return sharedPreferences.getBoolean(Preferences.IS_IMAGE_DISPLAYED, true)
    }

    override fun saveItem(text: String?, isMarked: Boolean, url: String?, id: Int) {
        sharedPreferences
            .edit()
            .putString(Preferences.ITEM_TEXT, text)
            .putBoolean(Preferences.ITEM_IS_MARKED, isMarked)
            .putString(Preferences.ITEM_URL, url)
            .putInt(Preferences.ITEM_ID, id)
            .apply()
    }

    override fun loadItem(): Item? {
        if(sharedPreferences.getInt(Preferences.ITEM_ID, -1) != -1) {
            sharedPreferences.getString(Preferences.ITEM_TEXT, null)?.let {
                return Item(
                    title = it,
                    isMarked = sharedPreferences.getBoolean(Preferences.ITEM_IS_MARKED, false),
                    url = sharedPreferences.getString(Preferences.ITEM_URL, null),
                    id = sharedPreferences.getInt(Preferences.ITEM_ID, -1)
                )
            }
        }
        return null
    }
}