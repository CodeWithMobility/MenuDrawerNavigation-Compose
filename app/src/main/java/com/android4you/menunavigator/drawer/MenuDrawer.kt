package com.android4you.menunavigator.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android4you.menunavigator.R
import com.android4you.menunavigator.drawer.model.NavigationItem
import com.android4you.menunavigator.drawer.views.NavigationItemView

@Composable
fun MenuDrawer(
    selectedNavigationItem: NavigationItem,
    onNavigationItemClick: (NavigationItem) -> Unit,
    onCloseClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(fraction = 0.5f)
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(60.dp))
        Image(
            modifier = Modifier
                .size(120.dp)
//                .shadow(
//                    Color.Yellow,
//                    alpha = 0.2f,
//                    shadowRadius = 60.dp
//                )
                .clip(CircleShape) // Clip the image to a circular shape
                .border(5.dp, Color(0xFFFFFFFF), CircleShape),
            painter = painterResource(id = R.drawable.profileimage),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Manu Aravind",
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Text(
            text = "Mobile Developer️",
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            fontFamily = FontFamily.Serif,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(horizontal = 12.dp)
        )
        Spacer(modifier = Modifier.height(14.dp))
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        NavigationItem.entries.toTypedArray().take(7).forEach { navigationItem ->
            NavigationItemView(
                navigationItem = navigationItem,
                selected = navigationItem == selectedNavigationItem,
                onClick = { onNavigationItemClick(navigationItem) }
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
        Spacer(modifier = Modifier.weight(1f))
        NavigationItem.entries.toTypedArray().takeLast(1).forEach { navigationItem ->
            NavigationItemView(
                navigationItem = navigationItem,
                selected = false,
                onClick = {
                    when (navigationItem) {
                        NavigationItem.Settings -> {
                            onNavigationItemClick(NavigationItem.Settings)
                        }

                        else -> {}
                    }
                }
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}