package com.ipcoding.colorfulshoppinglist.feature.domain.use_case

import com.ipcoding.colorfulshoppinglist.feature.domain.model.InvalidItemException
import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item
import com.ipcoding.colorfulshoppinglist.feature.domain.repository.ItemRepository
import java.io.File

class AddItem(
    private val repository: ItemRepository
) {

    @Throws(InvalidItemException::class)
    suspend operator fun invoke(title: String, isMarked : Boolean, url : String?, id: Int?) {
        if(title.isBlank()) {
            throw InvalidItemException("The Item can't be empty.")
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