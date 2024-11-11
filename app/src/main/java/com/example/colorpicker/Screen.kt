package com.example.colorpicker

sealed class Screen(
    val route: String
) {
    object Home : Screen("home")
    object ColorPicker : Screen("color_picker")
    object ImageColorPicker : Screen("image_color_picker")
}