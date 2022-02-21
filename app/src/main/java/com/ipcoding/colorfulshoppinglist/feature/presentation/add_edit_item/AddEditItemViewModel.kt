package com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipcoding.colorfulshoppinglist.core.domain.preferences.Preferences
import com.ipcoding.colorfulshoppinglist.feature.domain.model.InvalidItemException
import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item
import com.ipcoding.colorfulshoppinglist.feature.domain.use_case.ItemUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditItemViewModel @Inject constructor(
    private val itemUseCases: ItemUseCases,
    private val preferences: Preferences,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _itemTitle = mutableStateOf(ItemTextFieldState(hint = "Enter item..."))
    val itemTitle: State<ItemTextFieldState> = _itemTitle

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _currentUrl = mutableStateOf<String?>(null)
    val currentUrl: State<String?> = _currentUrl

    init {
        savedStateHandle.get<Int>("itemId")?.let { itemId ->
            if(itemId != -1) {
                viewModelScope.launch {
                    itemUseCases.getItem(itemId)?.also { item ->
                        item.id?.let { preferences.saveCurrentId(it) }
                        _itemTitle.value = itemTitle.value.copy(
                            text = item.title,
                            isHintVisible = false
                        )
                        _currentUrl.value = item.url
                        preferences.saveCurrentUrl(item.url)
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
            }
        }
    }

    fun onEvent(event: AddEditItemEvent) {
        when(event) {
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
            is AddEditItemEvent.ChangedImage -> {
                preferences.saveCurrentUrl(event.url)
            }
            is AddEditItemEvent.SaveItem -> {
                viewModelScope.launch {
                    try {
                        itemUseCases.addItem(
                            Item(
                                title = itemTitle.value.text,
                                url = preferences.loadCurrentUrl(),
                                id = if(preferences.loadCurrentId() == -1) null else
                                        preferences.loadCurrentId(),
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveItem)
                    } catch(e: InvalidItemException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Couldn't save item"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String): UiEvent()
        object SaveItem: UiEvent()
    }
}