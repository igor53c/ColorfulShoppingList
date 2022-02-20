package com.ipcoding.colorfulshoppinglist.feature.domain.use_case

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item
import com.ipcoding.colorfulshoppinglist.feature.domain.repository.ItemRepository
import com.ipcoding.colorfulshoppinglist.ui.theme.Colors

class ChangeColorItem (
    private val repository: ItemRepository
) {

    suspend operator fun invoke(item: Item) {
        val redPink =  Colors.RedPink.toArgb()
        val transparent = Color.Transparent.toArgb()
        item.color = if(item.color == redPink) transparent else redPink
        repository.insertItem(item)
    }
}