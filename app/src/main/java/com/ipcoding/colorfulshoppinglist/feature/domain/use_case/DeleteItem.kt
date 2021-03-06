package com.ipcoding.colorfulshoppinglist.feature.domain.use_case

import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item
import com.ipcoding.colorfulshoppinglist.feature.domain.repository.ItemRepository
import java.io.File

class DeleteItem(
    private val repository: ItemRepository
) {

    suspend operator fun invoke(item: Item) {
        item.url?.let { File(it).delete() }
        repository.deleteItem(item)
    }
}