package com.ipcoding.colorfulshoppinglist.feature.data.repository

import com.ipcoding.colorfulshoppinglist.feature.data.data_source.ItemDao
import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item
import com.ipcoding.colorfulshoppinglist.feature.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow

class ItemRepositoryImpl(
    private val dao: ItemDao
): ItemRepository {

    override fun getItemsFlow(): Flow<List<Item>> {
        return dao.getItemsFlow()
    }

    override suspend fun getItemById(id: Int?): Item? {
        return dao.getItemById(id)
    }

    override suspend fun insertItem(item: Item) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: Item) {
        dao.deleteItem(item)
    }
}