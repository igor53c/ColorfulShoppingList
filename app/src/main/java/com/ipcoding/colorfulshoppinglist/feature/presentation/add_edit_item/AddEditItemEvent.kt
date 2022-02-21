package com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item

import androidx.compose.ui.focus.FocusState

sealed class AddEditItemEvent {
    data class EnteredTitle(val value: String): AddEditItemEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditItemEvent()
    data class ChangedImage(val url: String?): AddEditItemEvent()
    object SaveItem: AddEditItemEvent()
}
