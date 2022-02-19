package com.ipcoding.einkaufsliste.feature_item.domain.use_case

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.ipcoding.einkaufsliste.feature_item.domain.model.Item
import com.ipcoding.einkaufsliste.feature_item.domain.repository.ItemRepository

class ChangeColorItem (
    private val repository: ItemRepository
) {

    suspend operator fun invoke(item: Item) {
        val red = Color.Red.toArgb()
        val green = Color.Green.toArgb()
        item.color = if(item.color == red) green else red
        repository.insertItem(item)
    }
}