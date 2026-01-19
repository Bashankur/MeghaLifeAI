package com.meghalife.app.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@Composable
fun StarRating(
    rating: Int,
    onRatingSelected: (Int) -> Unit,
    maxStars: Int = 5
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {

        (1..maxStars).forEach { star ->

            val isSelected = star <= rating

            Box(
                modifier = Modifier
                    .size(48.dp) // 🎯 Touch-friendly (Material guideline)
                    .clickable { onRatingSelected(star) }
                    .semantics {
                        contentDescription = "Rate $star star"
                    },
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector =
                        if (isSelected)
                            Icons.Filled.Star
                        else
                            Icons.Outlined.StarBorder,
                    contentDescription = null,
                    tint =
                        if (isSelected)
                            Color(0xFFFFC107) // ⭐ Gold
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
