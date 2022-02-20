package com.ipcoding.colorfulshoppinglist.feature.data.data_source

import androidx.room.*
import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * FROM item")
    fun getItemsFlow(): Flow<List<Item>>

    @Query("SELECT * FROM item WHERE id = :id")
    suspend fun getItemById(id: Int?): Item?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)
}