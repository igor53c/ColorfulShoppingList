package com.ipcoding.colorfulshoppinglist.feature.presentation.items

import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item
import com.ipcoding.colorfulshoppinglist.feature.domain.util.ItemOrder

sealed class ItemsEvent {
    data class Order(val itemOrder: ItemOrder): ItemsEvent()
    data class UpdateItem(val item: Item): ItemsEvent()
    data class OnDeleteItemClick(val item: Item): ItemsEvent()
    object DeleteItem: ItemsEvent()
    object CloseDeleteItemView: ItemsEvent()
    object ItemWithImage: ItemsEvent()
    object ItemWithoutImage: ItemsEvent()
}
