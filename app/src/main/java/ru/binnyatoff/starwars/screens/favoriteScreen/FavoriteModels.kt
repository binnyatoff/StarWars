package ru.binnyatoff.starwars.screens.favoriteScreen

import ru.binnyatoff.starwars.data.database.ResultMainDTO


sealed class FavoriteState {
    data object Empty : FavoriteState()
    data object Loading : FavoriteState()
    data class Loaded(val resultMain: List<ResultMainDTO>) : FavoriteState()
}

sealed class FavoriteEvent {
    data object ScreenInit : FavoriteEvent()
    data class DeleteItem(val resultMainDTO: ResultMainDTO) : FavoriteEvent()
}