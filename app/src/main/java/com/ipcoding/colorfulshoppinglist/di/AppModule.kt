package com.ipcoding.colorfulshoppinglist.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.ipcoding.colorfulshoppinglist.core.data.preferences.DefaultPreferences
import com.ipcoding.colorfulshoppinglist.core.domain.preferences.Preferences
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
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences): Preferences {
        return DefaultPreferences(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: ItemRepository): ItemUseCases {
        return ItemUseCases(
            getItems = GetItems(repository),
            deleteItem = DeleteItem(repository),
            changeItemIsMarked = ChangeItemIsMarked(repository),
            addItem = AddItem(repository),
            getItem = GetItem(repository)
        )
    }
}