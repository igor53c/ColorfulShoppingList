package com.ipcoding.colorfulshoppinglist.feature.domain.use_case

import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item
import com.ipcoding.colorfulshoppinglist.feature.domain.repository.ItemRepository

class UpdateItem(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(item: Item) {
        repository.insertItem(item)
    }
}