package com.ipcoding.colorfulshoppinglist.feature.presentation.items

import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item
import com.ipcoding.colorfulshoppinglist.feature.domain.util.ItemOrder
import com.ipcoding.colorfulshoppinglist.feature.domain.util.OrderType

data class ItemsState(
    var items: List<Item> = emptyList(),
    val itemOrder: ItemOrder = ItemOrder.IsMarked(OrderType.Ascending)
)
