package com.ipcoding.colorfulshoppinglist.di

import android.app.Application
import androidx.room.Room
import com.ipcoding.colorfulshoppinglist.feature.data.data_source.ItemDatabase
import com.ipcoding.colorfulshoppinglist.feature.data.repository.ItemRepositoryImpl
import com.ipcoding.colorfulshoppinglist.feature.domain.repository.ItemRepository
import com.ipcoding.colorfulshoppinglist.feature.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideItemDatabase(app: Application): ItemDatabase {
        return Room.databaseBuilder(
            app,
            ItemDatabase::class.java,
            ItemDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideItemRepository(db: ItemDatabase): ItemRepository {
        return ItemRepositoryImpl(db.itemDao)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: ItemRepository): ItemUseCases {
        return ItemUseCases(
            getItems = GetItems(repository),
            deleteItem = DeleteItem(repository),
            changeColorItem = ChangeColorItem(repository),
            addItem = AddItem(repository),
            getItem = GetItem(repository)
        )
    }
}