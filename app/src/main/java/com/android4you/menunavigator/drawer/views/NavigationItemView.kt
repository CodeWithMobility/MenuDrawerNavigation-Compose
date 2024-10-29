package com.android4you.menunavigator.drawer.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android4you.menunavigator.drawer.model.NavigationItem


@Composable
fun NavigationItemView(
    navigationItem: NavigationItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(size = 99.dp))
            .clickable { onClick() }
            .background(
                color = if (selected) Color.Gray.copy(alpha = 0.2f)
                else Color.Unspecified,
                shape = RoundedCornerShape(99.dp)
            )
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = navigationItem.icon,
            contentDescription = "Navigation Item Icon",
            tint = if (selected) Color.White
            else  Color.White.copy(0.8f)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = navigationItem.title,
            color = if (selected) Color.White
            else  Color.White.copy(0.8f),
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            lineHeight = 20.sp
        )
    }
}