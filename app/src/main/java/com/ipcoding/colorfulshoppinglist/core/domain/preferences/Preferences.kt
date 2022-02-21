package com.ipcoding.colorfulshoppinglist.core.domain.preferences

interface Preferences {

    fun saveCurrentUrl(url: String?)
    fun saveCurrentId(id: Int)
    fun saveCurrentText(text: String?)

    fun loadCurrentUrl(): String?
    fun loadCurrentId(): Int?
    fun loadCurrentText(): String?

    companion object {
        const val CURRENT_URL = "current_url"
        const val CURRENT_ID = "current_id"
        const val CURRENT_TEXT = "current_text"
    }
}