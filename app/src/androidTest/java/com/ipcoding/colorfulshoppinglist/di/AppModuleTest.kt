package com.ipcoding.colorfulshoppinglist.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.ipcoding.colorfulshoppinglist.core.data.preferences.DefaultPreferencesFake
import com.ipcoding.colorfulshoppinglist.core.data.resources.AndroidResourceProvider
import com.ipcoding.colorfulshoppinglist.core.domain.preferences.Preferences
import com.ipcoding.colorfulshoppinglist.core.domain.resources.ResourceProvider
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
object AppModuleTest {

    @Provides
    @Singleton
    fun provideItemDatabase(app: Application): ItemDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            ItemDatabase::class.java
        ).build()
    }

    @Provides
    @Singleton
    fun provideItemRepository(db: ItemDatabase): ItemRepository {
        return ItemRepositoryImpl(db.itemDao)
    }

    @Provides
    @Singleton
    fun providePreferences(): Preferences {
        return DefaultPreferencesFake()
    }

    @Provides
    @Singleton
    fun provideContext(app: Application): Context {
        return app.applicationContext
    }

    @Provides
    @Singleton
    fun provideAndroidResources(context: Context): ResourceProvider {
        return AndroidResourceProvider(context)
    }

    @Provides
    @Singleton
    fun provideUseCases(
        repository: ItemRepository,
        resourceProvider: ResourceProvider
    ): ItemUseCases {
        return ItemUseCases(
            getItems = GetItems(repository),
            deleteItem = DeleteItem(repository),
            changeItemIsMarked = ChangeItemIsMarked(repository),
            addItem = AddItem(repository, resourceProvider),
            getItem = GetItem(repository),
            updateItem = UpdateItem(repository)
        )
    }
}