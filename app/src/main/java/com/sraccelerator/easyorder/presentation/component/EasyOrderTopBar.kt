package com.sraccelerator.easyorder.presentation.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sraccelerator.easyorder.EasyOrderApp
import com.sraccelerator.easyorder.presentation.theme.Background
import com.sraccelerator.easyorder.presentation.theme.OnBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EasyOrderTopBar(
    @StringRes titleRes: Int,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = titleRes),
                style = TextStyle(
                    color = OnBackground,
                    fontWeight = FontWeight.Black,
                    fontSize = 20.sp,
                )
            )
        },
        navigationIcon = navigationIcon,
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Background
        )
    )
}