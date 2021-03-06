package com.ipcoding.colorfulshoppinglist.feature.domain.use_case

data class ItemUseCases(
    val getItems: GetItems,
    val deleteItem: DeleteItem,
    val changeItemIsMarked: ChangeItemIsMarked,
    val addItem: AddItem,
    val getItem: GetItem,
    val updateItem: UpdateItem
)
