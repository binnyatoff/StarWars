package ru.binnyatoff.starwars.data.network.models.starships

data class Starships(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<ResultStarships>
)