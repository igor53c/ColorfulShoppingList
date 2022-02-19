package com.ipcoding.einkaufsliste.feature_item.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    val title: String,
    var color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val itemColors = listOf(Color.Red, Color.Green)
    }
}

class InvalidItemExeption(message: String): Exception(message)
