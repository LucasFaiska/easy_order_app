package com.sraccelerator.easyorder.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sraccelerator.easyorder.R
import com.sraccelerator.easyorder.data.model.Category
import com.sraccelerator.easyorder.presentation.theme.*

@Composable
fun EasyOrderCategoryCard(
    category: Category,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceVariant)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            CategoryBackground(category)
            CategoryContent(category)
        }
    }
}

@Composable
private fun CategoryBackground(category: Category) {
    AsyncImage(
        model = category.imageUrl,
        contentDescription = stringResource(R.string.cd_category_image),
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f))
                )
            )
    )
}

@Composable
private fun BoxScope.CategoryContent(category: Category) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CategoryInfo(category, modifier = Modifier.weight(1f))
        CategoryActionButton()
    }
}

@Composable
private fun CategoryInfo(category: Category, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = category.name,
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = (-0.5).sp
            )
        )
        Text(
            text = stringResource(R.string.category_explore_options),
            style = TextStyle(
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp
            )
        )
    }
}

@Composable
private fun CategoryActionButton() {
    Surface(
        shape = CircleShape,
        color = Primary,
        modifier = Modifier.size(44.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = stringResource(R.string.cd_navigate_to_category),
            tint = OnPrimary,
            modifier = Modifier.padding(10.dp)
        )
    }
}