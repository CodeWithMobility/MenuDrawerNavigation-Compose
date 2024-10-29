package com.android4you.menunavigator.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android4you.menunavigator.drawer.state.MenuDrawerState
import com.android4you.menunavigator.drawer.state.isOpened
import com.android4you.menunavigator.drawer.state.opposite
import kotlinx.serialization.Serializable
@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    drawerState: MenuDrawerState,
    onDrawerClick: (MenuDrawerState) -> Unit,
    navController: NavHostController
) {
    // Reusable modifier chain for NavHost
    val navHostModifier = modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(if (drawerState.isOpened()) 16.dp else 0.dp))
        .background(Color.White)
        .clickable(enabled = drawerState == MenuDrawerState.Opened) {
            onDrawerClick(MenuDrawerState.Closed)
        }

    // Reusable lambda for handling drawer clicks
    val onDrawerClickLambda: () -> Unit = {
        onDrawerClick(drawerState.opposite())
    }

    NavHost(
        modifier = navHostModifier,
        navController = navController,
        startDestination = Routes.HomeScreen
    ) {
        composable<Routes.HomeScreen> { HomeScreen(onDrawerClickLambda) }
        composable<Routes.TasksScreen> { TasksScreen(onDrawerClickLambda) }
        composable<Routes.ProjectsScreen> { ProjectsScreen(onDrawerClickLambda) }
        composable<Routes.RemindersScreen> { RemindersScreen(onDrawerClickLambda) }
        composable<Routes.CalendarScreen> { CalendarScreen(onDrawerClickLambda) }
        composable<Routes.ReportsScreen> { ReportsScreen(onDrawerClickLambda) }
        composable<Routes.ProfileScreen> { ProfileScreen(onDrawerClickLambda) }
        composable<Routes.SettingsScreen> { SettingsScreen(onDrawerClickLambda) }
    }
}

@Serializable
sealed class Routes {
    @Serializable
    data object HomeScreen : Routes()

    @Serializable
    data object TasksScreen : Routes()

    @Serializable
    data object ProjectsScreen : Routes()

    @Serializable
    data object RemindersScreen : Routes()

    @Serializable
    data object CalendarScreen : Routes()

    @Serializable
    data object ReportsScreen : Routes()

    @Serializable
    data object SettingsScreen : Routes()

    @Serializable
    data object ProfileScreen : Routes()
}