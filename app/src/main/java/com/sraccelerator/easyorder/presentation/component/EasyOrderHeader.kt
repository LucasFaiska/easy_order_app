package com.sraccelerator.easyorder.presentation.component

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sraccelerator.easyorder.presentation.theme.OnBackground

@Composable
fun EasyOrderHeader(
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = titleRes).uppercase(),
        modifier = modifier,
        style = TextStyle(
            color = OnBackground,
            fontSize = 32.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = (-1).sp,
            lineHeight = 32.sp
        )
    )
}