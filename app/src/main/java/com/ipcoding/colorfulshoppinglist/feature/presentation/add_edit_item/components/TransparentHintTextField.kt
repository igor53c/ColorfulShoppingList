package com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme

@Composable
fun TransparentHintTextField(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    testTag: String = "",
    onFocusChange: (FocusState) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        TextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            maxLines = 5,
            textStyle = textStyle,
            modifier = Modifier
                .testTag(testTag)
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusChange(it)
                },
            colors = TextFieldDefaults.textFieldColors(
                textColor = AppTheme.colors.primary,
                backgroundColor = Color.Transparent,
                cursorColor = AppTheme.colors.primary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            )
        )
        if(isHintVisible) {
            Text(
                text = hint,
                style = textStyle,
                color = AppTheme.colors.primary,
                modifier = Modifier.padding(AppTheme.dimensions.spaceMedium)
            )
        }
    }
}