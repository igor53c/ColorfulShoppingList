package com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Button
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

    markingColor.value = if(isMarked) AppTheme.colors.primary else AppTheme.colors.background

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
            Icon(
                imageVector = Icons.Default.Save,
                contentDescription = stringResource(id = R.string.save),
                tint = AppTheme.colors.primary,
                modifier = Modifier
                    .size(AppTheme.dimensions.spaceExtraLarge)
                    .clickable { onSaveClick() }

            )

            Spacer(modifier = Modifier.height(AppTheme.dimensions.spaceLarge))

            Box(
                modifier = Modifier
                    .border(
                        width = AppTheme.dimensions.spaceExtraSmall,
                        color = AppTheme.colors.primary,
                        shape = AppTheme.customShapes.roundedCornerShape
                    )
                    .background(
                        color = markingColor.value,
                        shape = AppTheme.customShapes.roundedCornerShape
                    )
                    .size(AppTheme.dimensions.spaceExtraLarge)
                    .clickable { onMarkingClick() }
            ) {

            }
        }
    }
}