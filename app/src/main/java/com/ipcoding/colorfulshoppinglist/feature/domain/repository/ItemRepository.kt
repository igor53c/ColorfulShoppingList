package com.ipcoding.colorfulshoppinglist.feature.domain.repository

import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    fun getItemsFlow(): Flow<List<Item>>

    suspend fun getItemById(id: Int?): Item?

    suspend fun insertItem(item: Item)

    suspend fun deleteItem(item: Item)
}