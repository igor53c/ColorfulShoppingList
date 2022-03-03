package com.ipcoding.colorfulshoppinglist.feature.domain.use_case

import com.ipcoding.colorfulshoppinglist.R
import com.ipcoding.colorfulshoppinglist.core.domain.resources.ResourceProvider
import com.ipcoding.colorfulshoppinglist.feature.domain.model.InvalidItemException
import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item
import com.ipcoding.colorfulshoppinglist.feature.domain.repository.ItemRepository
import java.io.File

class AddItem(
    private val repository: ItemRepository,
    private val resourceProvider: ResourceProvider,
) {

    @Throws(InvalidItemException::class)
    suspend operator fun invoke(title: String, isMarked: Boolean, url: String?, id: Int?) {
        if (title.isBlank()) {
            throw InvalidItemException(resourceProvider.getString(R.string.item_cant_empty))
        }
        repository.getItemById(id)?.let { item ->
            item.url?.let { itemUrl ->
                if (itemUrl != url) File(itemUrl).delete()
            }
        }
        repository.insertItem(
            Item(
                title = title,
                isMarked = isMarked,
                url = url,
                id = id
            )
        )
    }
}