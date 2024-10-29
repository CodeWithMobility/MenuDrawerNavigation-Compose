package com.android4you.menunavigator.drawer.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavigationItem(
    val title: String,
    val icon: ImageVector
) {
    Home(
        icon = Icons.Default.Home,
        title = "Home"
    ),
    Task(
        icon = Icons.Default.Build,
        title = "Tasks"
    ),
    Project(
        icon = Icons.Default.AccountBox,
        title = "Projects"
    ),
    Reminders(
        icon = Icons.Default.Notifications,
        title = "Reminders"
    ),
    Calendar(
        icon = Icons.Default.DateRange,
        title = "Calendar"
    ),
    Profile(
        icon = Icons.Default.Person,
        title = "Profile"
    ),
    Reports(
        icon = Icons.Default.Info,
        title = "Reports"
    ),
    Settings(
        icon = Icons.Default.Settings,
        title = "Settings"
    )
}