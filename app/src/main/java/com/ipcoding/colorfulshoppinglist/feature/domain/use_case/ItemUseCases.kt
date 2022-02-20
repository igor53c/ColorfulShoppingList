package com.ipcoding.colorfulshoppinglist.feature.domain.use_case

data class ItemUseCases(
    val getItems: GetItems,
    val deleteItem: DeleteItem,
    val changeColorItem: ChangeColorItem,
    val addItem: AddItem,
    val getItem: GetItem
)
