package com.ipcoding.colorfulshoppinglist.feature.domain.util

sealed class ItemOrder(var orderType: OrderType) {

    class Title(orderType: OrderType) : ItemOrder(orderType)
    class IsMarked(orderType: OrderType) : ItemOrder(orderType)
}

