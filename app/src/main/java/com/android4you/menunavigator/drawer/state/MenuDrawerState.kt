package com.android4you.menunavigator.drawer.state


enum class MenuDrawerState {
    Opened,
    Closed
}

fun MenuDrawerState.isOpened(): Boolean {
    return this.name == "Opened"
}

fun MenuDrawerState.opposite(): MenuDrawerState {
    return if (this == MenuDrawerState.Opened) MenuDrawerState.Closed else MenuDrawerState.Opened
}