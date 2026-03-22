package com.sraccelerator.easyorder.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sraccelerator.easyorder.R
import com.sraccelerator.easyorder.presentation.theme.OnBackground
import com.sraccelerator.easyorder.presentation.theme.OnPrimary
import com.sraccelerator.easyorder.presentation.theme.Primary

@Composable
fun EasyOrderCartBadge(
    count: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick, modifier = modifier) {
        BadgedBox(
            badge = {
                if (count > 0) {
                    Badge(
                        containerColor = Primary,
                        contentColor = OnPrimary
                    ) {
                        Text(text = count.toString())
                    }
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = stringResource(R.string.cart_icon_content_description),
                tint = OnBackground
            )
        }
    }
}