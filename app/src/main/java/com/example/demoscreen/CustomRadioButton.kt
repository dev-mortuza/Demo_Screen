package com.example.demoscreen

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CustomRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: Int = R.drawable.ic_selected_radio_btn,
    unselectedIcon: Int = R.drawable.ic_unselected_radio_btn,
    contentDescription: String? = null
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(24.dp)
    ) {
        Icon(
            painter = painterResource(id = if (selected) selectedIcon else unselectedIcon),
            contentDescription = contentDescription,
            tint = Color.Unspecified // keeps original icon color
        )
    }
}
