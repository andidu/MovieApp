package com.adorastudios.movieapp.presentation.utils

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object DetailsScreen: Screen("details_screen")

    companion object {
        const val PARAM_ID = "id"

        fun detailsScreen(id: Long): String {
            return DetailsScreen.route + "?$PARAM_ID=$id"
        }
    }
}
