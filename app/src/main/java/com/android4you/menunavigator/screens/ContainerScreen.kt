package com.android4you.menunavigator.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.android4you.menunavigator.drawer.MenuDrawer
import com.android4you.menunavigator.drawer.model.NavigationItem
import com.android4you.menunavigator.drawer.modifier.conditionalModifier
import com.android4you.menunavigator.drawer.state.MenuDrawerState
import com.android4you.menunavigator.drawer.state.isOpened

@Composable
fun ContainerScreen() {
    var drawerState by remember { mutableStateOf(MenuDrawerState.Closed) }
    var selectedNavigationItem by remember { mutableStateOf(NavigationItem.Home) }
    val navController = rememberNavController()
    val navigationRoutes = mapOf(
        NavigationItem.Home to Routes.HomeScreen,
        NavigationItem.Task to Routes.TasksScreen,
        NavigationItem.Project to Routes.ProjectsScreen,
        NavigationItem.Reminders to Routes.RemindersScreen,
        NavigationItem.Calendar to Routes.CalendarScreen,
        NavigationItem.Profile to Routes.ProfileScreen,
        NavigationItem.Reports to Routes.ReportsScreen,
        NavigationItem.Settings to Routes.SettingsScreen
    )
    BackHandler(enabled = drawerState.isOpened()) {
        drawerState = MenuDrawerState.Closed
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFF182037))
//                .statusBarsPadding()
                  .navigationBarsPadding()
                .fillMaxSize()
        ) {
            MenuDrawer(
                selectedNavigationItem = selectedNavigationItem,
                onNavigationItemClick = {
                    selectedNavigationItem = it
                    val route = navigationRoutes[it]
                    if (route != null) {
                        navController.navigate(route) {
                            // Pop up to HomeScreen for all but the Home item
//                            if (it != NavigationItem.Home) {
//                                popUpTo(Routes.HomeScreen) { inclusive = true }
//                            }
                            popUpTo(0) // Clear all previous screens from the back stack.
                            launchSingleTop = true
                        }
                    }
                    drawerState = MenuDrawerState.Closed
                },
                onCloseClick = { drawerState = MenuDrawerState.Closed }
            )
            MainContent(
                modifier = Modifier.conditionalModifier(5, drawerState),
                drawerState = drawerState,
                onDrawerClick = { drawerState = it },
                navController = navController
            )
        }
    }
}
