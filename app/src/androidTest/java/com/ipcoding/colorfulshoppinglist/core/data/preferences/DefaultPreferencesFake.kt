package com.ipcoding.colorfulshoppinglist.core.data.preferences

import com.ipcoding.colorfulshoppinglist.core.domain.preferences.Preferences
import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item

class DefaultPreferencesFake : Preferences {

    private var currentUrl: String? = null
    private var currentText: String? = null
    private var currentIsMarked: Boolean = false
    private var currentId: Int = -1
    private var isImageDisplayedValue: Boolean = true
    private var item: Item? = null

    override fun saveCurrentUrl(url: String?) {
        currentUrl = url
    }

    override fun saveCurrentText(text: String?) {
        currentText = text
    }

    override fun saveCurrentIsMarked(isMarked: Boolean) {
        currentIsMarked = isMarked
    }

    override fun saveCurrentId(id: Int) {
        currentId = id
    }

    override fun loadCurrentUrl(): String? {
        return currentUrl
    }

    override fun loadCurrentText(): String? {
        return currentText
    }

    override fun loadCurrentIsMarked(): Boolean {
        return currentIsMarked
    }

    override fun loadCurrentId(): Int {
        return currentId
    }

    override fun saveIsImageDisplayed(isImageDisplayed: Boolean) {
        isImageDisplayedValue = isImageDisplayed
    }

    override fun loadIsImageDisplayed(): Boolean {
        return isImageDisplayedValue
    }

    override fun saveItem(text: String?, isMarked: Boolean, url: String?, id: Int) {
        item = text?.let {
            Item(
                title = it,
                isMarked = isMarked,
                url = url,
                id = id
            )
        }
    }

    override fun loadItem(): Item? {
        if(item?.id != -1) {
            item?.title?.let {
                return item
            }
        }
        return null
    }
}