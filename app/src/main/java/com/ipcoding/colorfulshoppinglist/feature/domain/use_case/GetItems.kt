package com.ipcoding.colorfulshoppinglist.feature.domain.use_case

import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item
import com.ipcoding.colorfulshoppinglist.feature.domain.repository.ItemRepository
import com.ipcoding.colorfulshoppinglist.feature.domain.util.ItemOrder
import com.ipcoding.colorfulshoppinglist.feature.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetItems(
    private val repository: ItemRepository
) {

    operator fun invoke(
        itemOrder: ItemOrder = ItemOrder.Color(OrderType.Ascending)
    ): Flow<List<Item>> {
        return repository.getItemsFlow().map { items ->
            when (itemOrder.orderType) {
                is OrderType.Ascending -> {
                    when (itemOrder) {
                        is ItemOrder.Title -> items.sortedBy { it.title.lowercase() }
                        is ItemOrder.Color -> items.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when (itemOrder) {
                        is ItemOrder.Title -> items.sortedByDescending { it.title.lowercase() }
                        is ItemOrder.Color -> items.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}