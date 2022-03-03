package com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ipcoding.colorfulshoppinglist.R
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme

@Composable
fun TopRow(
    onSaveClick: () -> Unit,
    onMarkingClick: () -> Unit,
    isMarked: Boolean,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val markingColor = remember { mutableStateOf(Color.Transparent) }

    markingColor.value = if (isMarked) AppTheme.colors.primary else AppTheme.colors.background

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PicturePart(
            modifier = Modifier.weight(1f),
            navController = navController
        )

        Column(
            modifier = Modifier.padding(end = AppTheme.dimensions.spaceMedium)
        ) {
            IconButton(
                onClick = onSaveClick
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = stringResource(id = R.string.save),
                    tint = AppTheme.colors.primary,
                    modifier = Modifier
                        .size(AppTheme.dimensions.spaceExtraLarge)
                )
            }

            Spacer(modifier = Modifier.height(AppTheme.dimensions.spaceLarge))

            Button(
                onClick = onMarkingClick,
                modifier = Modifier
                    .size(AppTheme.dimensions.spaceExtraLarge)
                    .shadow(
                        elevation = AppTheme.dimensions.spaceExtraSmall,
                        shape = AppTheme.customShapes.roundedCornerShape
                    )
                    .clip(AppTheme.customShapes.roundedCornerShape)
                    .border(
                        width = AppTheme.dimensions.spaceExtraSmall,
                        color = AppTheme.colors.primary,
                        shape = AppTheme.customShapes.roundedCornerShape
                    )
                    .background(
                        color = markingColor.value,
                        shape = AppTheme.customShapes.roundedCornerShape
                    ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = markingColor.value
                )
            ) {}
        }
    }
}