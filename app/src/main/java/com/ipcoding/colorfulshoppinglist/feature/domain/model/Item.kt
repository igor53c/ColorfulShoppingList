package com.ipcoding.colorfulshoppinglist.feature.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    val title: String,
    var color: Int,
    @PrimaryKey val id: Int? = null
)

class InvalidItemException(message: String): Exception(message)
