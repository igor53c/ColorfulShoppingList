package com.ipcoding.colorfulshoppinglist.feature.presentation.items

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipcoding.colorfulshoppinglist.core.domain.preferences.Preferences
import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item
import com.ipcoding.colorfulshoppinglist.feature.domain.use_case.ItemUseCases
import com.ipcoding.colorfulshoppinglist.feature.domain.util.ItemOrder
import com.ipcoding.colorfulshoppinglist.feature.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val itemUseCases: ItemUseCases,
    private val preferences: Preferences
) : ViewModel() {

    private val _state = mutableStateOf(ItemsState())
    val state: State<ItemsState> = _state

    private val _isImageDisplayed = mutableStateOf(true)
    val isImageDisplayed: State<Boolean> = _isImageDisplayed

    private val _isDeleteDialogDisplayed = mutableStateOf(false)
    val isDeleteDialogDisplayed: State<Boolean> = _isDeleteDialogDisplayed

    private var itemToDelete: Item? = null

    private var getItemsJob: Job? = null

    init {
        getItems(ItemOrder.IsMarked(OrderType.Ascending))
        clearCurrentItem()
        loadIsImageDisplayed()
    }

    private fun loadIsImageDisplayed() {
        _isImageDisplayed.value = preferences.loadIsImageDisplayed()
    }

    private fun clearCurrentItem() {
        preferences.saveCurrentId(-1)
        preferences.saveCurrentText(null)
        preferences.loadCurrentUrl()?.let { File(it).delete() }
        preferences.saveCurrentUrl(null)
        preferences.saveCurrentIsMarked(false)
        preferences.loadItem()?.let { item ->
            if (item.id != -1) {
                viewModelScope.launch {
                    itemUseCases.updateItem(item)
                }
                preferences.saveItem(null, false, null, -1)
            }
        }
    }

    fun onEvent(event: ItemsEvent) {
        when (event) {
            is ItemsEvent.Order -> {
                if (state.value.itemOrder::class == event.itemOrder::class) {
                    when (state.value.itemOrder.orderType) {
                        is OrderType.Ascending -> {
                            event.itemOrder.orderType = OrderType.Descending
                        }
                        is OrderType.Descending -> {
                            event.itemOrder.orderType = OrderType.Ascending
                        }
                    }
                }
                getItems(event.itemOrder)
            }
            is ItemsEvent.UpdateItem -> {
                viewModelScope.launch {
                    itemUseCases.changeItemIsMarked(event.item)
                }
                val list = mutableListOf<Item>()
                list.addAll(state.value.items)
                list.remove(event.item)
                _state.value.items = list
            }
            is ItemsEvent.DeleteItem -> {
                viewModelScope.launch {
                    itemToDelete?.let { itemUseCases.deleteItem(it) }
                }
                _isDeleteDialogDisplayed.value = false
                itemToDelete = null
            }
            is ItemsEvent.OnDeleteItemClick -> {
                _isDeleteDialogDisplayed.value = true
                itemToDelete = event.item
            }
            is ItemsEvent.CloseDeleteItemView -> {
                _isDeleteDialogDisplayed.value = false
            }
            is ItemsEvent.ItemWithImage -> {
                _isImageDisplayed.value = true
                preferences.saveIsImageDisplayed(true)
            }
            is ItemsEvent.ItemWithoutImage -> {
                _isImageDisplayed.value = false
                preferences.saveIsImageDisplayed(false)
            }
        }
    }

    private fun getItems(itemOrder: ItemOrder) {
        getItemsJob?.cancel()
        getItemsJob = itemUseCases.getItems(itemOrder)
            .onEach { items ->
                _state.value = state.value.copy(
                    items = items,
                    itemOrder = itemOrder
                )
            }
            .launchIn(viewModelScope)
    }
}