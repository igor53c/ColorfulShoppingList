package com.ipcoding.colorfulshoppinglist.feature.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item

@Database(
    entities = [Item::class],
    version = 1
)
abstract class ItemDatabase : RoomDatabase() {

    abstract val itemDao: ItemDao

    companion object {
        const val DATABASE_NAME = "items_db"
    }
}