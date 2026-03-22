package com.sraccelerator.easyorder.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sraccelerator.easyorder.R
import com.sraccelerator.easyorder.data.model.Product
import com.sraccelerator.easyorder.presentation.theme.OnBackground
import com.sraccelerator.easyorder.presentation.theme.OnPrimary
import com.sraccelerator.easyorder.presentation.theme.OnPrimaryContainer
import com.sraccelerator.easyorder.presentation.theme.OnSurfaceVariant
import com.sraccelerator.easyorder.presentation.theme.Outline
import com.sraccelerator.easyorder.presentation.theme.Primary
import com.sraccelerator.easyorder.presentation.theme.SurfaceVariant

@Composable
fun EasyOrderProductCard(
    product: Product,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceVariant)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column {
                ProductThumbnail(product.imageUrl)
                ProductInfo(product, onAddClick)
            }

            if (product.isPromoted) {
                PromotionBadge(modifier = Modifier.align(Alignment.TopStart))
            }
        }
    }
}

@Composable
private fun ProductThumbnail(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = stringResource(R.string.product_cd_image),
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun ProductInfo(product: Product, onAddClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(
            text = product.name,
            style = TextStyle(
                color = OnBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = "${product.preparationTime}${stringResource(R.string.product_label_prep_time)}",
            style = TextStyle(
                color = OnSurfaceVariant,
                fontSize = 11.sp
            ),
            modifier = Modifier.padding(vertical = 4.dp)
        )

        PriceSection(
            price = product.price,
            originalPrice = product.originalPrice,
            onAddClick = onAddClick
        )
    }
}

@Composable
private fun PriceSection(
    price: Double,
    originalPrice: Double?,
    onAddClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "${stringResource(R.string.product_currency_symbol)} $price",
                style = TextStyle(
                    color = Primary,
                    fontWeight = FontWeight.Black,
                    fontSize = 15.sp
                )
            )
            originalPrice?.let {
                Text(
                    text = "${stringResource(R.string.product_currency_symbol)} $it",
                    style = TextStyle(
                        color = Outline,
                        fontSize = 11.sp,
                        textDecoration = TextDecoration.LineThrough
                    )
                )
            }
        }

        AddActionButton(onClick = onAddClick)
    }
}

@Composable
private fun AddActionButton(onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = CircleShape,
        color = Primary,
        modifier = Modifier.size(32.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.product_cd_add_button),
            tint = OnPrimary,
            modifier = Modifier.padding(6.dp)
        )
    }
}

@Composable
private fun PromotionBadge(modifier: Modifier = Modifier) {
    Surface(
        color = OnPrimaryContainer,
        shape = RoundedCornerShape(bottomEnd = 12.dp),
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.product_label_promotion),
            color = OnPrimary,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}