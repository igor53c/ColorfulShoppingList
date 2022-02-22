package com.ipcoding.colorfulshoppinglist.core.domain.preferences

import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item

interface Preferences {

    fun saveCurrentUrl(url: String?)
    fun saveCurrentText(text: String?)
    fun saveCurrentIsMarked(isMarked: Boolean)
    fun saveCurrentId(id: Int)

    fun loadCurrentUrl(): String?
    fun loadCurrentText(): String?
    fun loadCurrentIsMarked(): Boolean
    fun loadCurrentId(): Int

    fun saveItem(text: String?, isMarked: Boolean, url: String?, id: Int)
    fun loadItem() : Item?

    companion object {
        const val CURRENT_URL = "current_url"
        const val CURRENT_ID = "current_id"
        const val CURRENT_TEXT = "current_text"
        const val CURRENT_IS_MARKED = "current_is_marked"

        const val ITEM_URL = "item_url"
        const val ITEM_ID = "item_id"
        const val ITEM_TEXT = "item_text"
        const val ITEM_IS_MARKED = "item_is_marked"
    }
}