package ru.binnyatoff.starwars.screens.homeScreen

import ru.binnyatoff.starwars.data.database.ResultMainDTO

sealed class HomeState() {
    data object Empty : HomeState()
    data class Loaded(val list: List<ResultMainDTO>) : HomeState()
    data object Loading : HomeState()
    data class Error(val e: String) : HomeState()
}

sealed class HomeEvent()  {
    data class LoadPeople(val searchQuery: String) : HomeEvent()
    data class AddToFavorite(val resultMain: ResultMainDTO) : HomeEvent()
}

interface ObtainEvent {
    fun obtainEvent(event: HomeEvent)
}