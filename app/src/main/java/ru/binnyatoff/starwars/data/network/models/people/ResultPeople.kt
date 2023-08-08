package ru.binnyatoff.starwars.data.network.models.people

data class ResultPeople(
    val gender: String,
    val name: String,
    val starships: List<String>,
    val films: List<String>,
    )
