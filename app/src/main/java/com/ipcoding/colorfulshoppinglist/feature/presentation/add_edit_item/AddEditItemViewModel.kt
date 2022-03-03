package com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipcoding.colorfulshoppinglist.R
import com.ipcoding.colorfulshoppinglist.core.domain.preferences.Preferences
import com.ipcoding.colorfulshoppinglist.core.domain.resources.ResourceProvider
import com.ipcoding.colorfulshoppinglist.feature.domain.model.InvalidItemException
import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item
import com.ipcoding.colorfulshoppinglist.feature.domain.use_case.ItemUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AddEditItemViewModel @Inject constructor(
    private val itemUseCases: ItemUseCases,
    private val preferences: Preferences,
    private val resourceProvider: ResourceProvider,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _itemTitle = mutableStateOf(
        ItemTextFieldState(
            hint = resourceProvider.getString(R.string.enter_item)
        )
    )
    val itemTitle: State<ItemTextFieldState> = _itemTitle

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _currentUrl = mutableStateOf<String?>(null)
    val currentUrl: State<String?> = _currentUrl

    private val _currentIsMarked = mutableStateOf(false)
    val currentIsMarked: State<Boolean> = _currentIsMarked

    private var currentItem: Item? = null

    init {
        savedStateHandle.get<Int>("itemId")?.let { itemId ->
            if (itemId != -1) {
                viewModelScope.launch {
                    itemUseCases.getItem(itemId)?.also { item ->
                        currentItem = item
                        _currentIsMarked.value = item.isMarked
                        _itemTitle.value = itemTitle.value.copy(
                            text = item.title,
                            isHintVisible = false
                        )
                        _currentUrl.value = item.url
                        item.id?.let { id ->
                            preferences.saveItem(
                                text = item.title,
                                isMarked = item.isMarked,
                                url = item.url,
                                id = id
                            )
                            preferences.saveCurrentId(id)
                        }
                        preferences.saveCurrentIsMarked(item.isMarked)
                        preferences.saveCurrentText(item.title)
                    }
                }
            } else {
                preferences.loadCurrentText()?.let {
                    _itemTitle.value = itemTitle.value.copy(
                        text = it,
                        isHintVisible = false
                    )
                }
                _currentUrl.value = preferences.loadCurrentUrl()
                _currentIsMarked.value = preferences.loadCurrentIsMarked()
            }
        }
    }

    fun onEvent(event: AddEditItemEvent) {
        when (event) {
            is AddEditItemEvent.EnteredTitle -> {
                _itemTitle.value = itemTitle.value.copy(
                    text = event.value
                )
                preferences.saveCurrentText(event.value)
            }
            is AddEditItemEvent.ChangeTitleFocus -> {
                _itemTitle.value = itemTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            itemTitle.value.text.isBlank()
                )
            }
            is AddEditItemEvent.DeleteImage -> {
                preferences.saveCurrentUrl(null)
                _currentUrl.value = null
                viewModelScope.launch {
                    currentItem?.let { item ->
                        item.url = null
                        itemUseCases.updateItem(item)
                    }
                }
                event.url?.let { File(it).delete() }
            }
            is AddEditItemEvent.MarkingItem -> {
                _currentIsMarked.value = !currentIsMarked.value
                preferences.saveCurrentIsMarked(_currentIsMarked.value)
            }
            is AddEditItemEvent.SaveItem -> {
                viewModelScope.launch {
                    try {
                        itemUseCases.addItem(
                            title = itemTitle.value.text,
                            url = currentUrl.value,
                            isMarked = currentIsMarked.value,
                            id = if (preferences.loadCurrentId() == -1) null else
                                preferences.loadCurrentId(),
                        )
                        _eventFlow.emit(UiEvent.SaveItem)
                        clearCurrentItem()
                    } catch (e: InvalidItemException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message
                                    ?: resourceProvider.getString(R.string.could_not_save_item)
                            )
                        )
                    }
                }
            }
        }
    }

    private fun clearCurrentItem() {
        preferences.saveCurrentId(-1)
        preferences.saveCurrentText(null)
        preferences.saveCurrentUrl(null)
        preferences.saveCurrentIsMarked(false)
        preferences.saveItem(null, false, null, -1)
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object SaveItem : UiEvent()
    }
}