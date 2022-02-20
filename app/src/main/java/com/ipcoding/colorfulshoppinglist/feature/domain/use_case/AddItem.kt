package com.ipcoding.colorfulshoppinglist.feature.domain.use_case

import com.ipcoding.colorfulshoppinglist.feature.domain.model.InvalidItemException
import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item
import com.ipcoding.colorfulshoppinglist.feature.domain.repository.ItemRepository

class AddItem(
    private val repository: ItemRepository
) {

    @Throws(InvalidItemException::class)
    suspend operator fun invoke(item: Item) {
        if(item.title.isBlank()) {
            throw InvalidItemException("The Item can't be empty.")
        }
        repository.insertItem(item)
    }
}