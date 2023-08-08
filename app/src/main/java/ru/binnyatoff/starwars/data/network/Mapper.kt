package ru.binnyatoff.starwars.data.network

import ru.binnyatoff.starwars.data.database.ResultMainDTO
import ru.binnyatoff.starwars.data.network.models.TypeOfResult
import ru.binnyatoff.starwars.data.network.models.people.ResultPeople
import ru.binnyatoff.starwars.data.network.models.planets.ResultPlanets
import ru.binnyatoff.starwars.data.network.models.starships.ResultStarships

fun ResultPeople.toResultMainDTO(): ResultMainDTO {
    return ResultMainDTO(
        type = TypeOfResult.PEOPLE,
        name = this.name,
        diameter = null,
        population = null,
        gender = this.gender,
        starships = this.starships.size,
        films = this.films.map { filmUrl->
            filmUrlToFilmTitle(filmUrl)
        }.toString(),
        manufacturer = null,
        model = null,
        passengers = null,
    )
}

fun ResultPlanets.toResultMainDTO(): ResultMainDTO{
    return ResultMainDTO(
        type = TypeOfResult.PLANETS,
        name = name,
        diameter= diameter,
        population = population,
        gender = null,
        starships = null,
        films = null,
        manufacturer = null,
        model = null,
        passengers = null,
    )
}

fun ResultStarships.toResultMainDTO():ResultMainDTO{
    return ResultMainDTO(
        type = TypeOfResult.STARSHIPS,
        name= name,
        diameter = null,
        population = null,
        gender = null,
        starships = null,
        films = null,
        manufacturer=manufacturer,
        model = model,
        passengers = passengers,
    )
}


fun filmUrlToFilmTitle(filmUrl: String): String? {
    val filmId: Char = try {
        filmUrl[filmUrl.length - 2]
    } catch (e: Exception) {
        return null
    }
    return when (filmId) {
        '1' -> "Star Wars Episode IV A New Hope"
        '2' -> "Star Wars Episode V The Empire Strikes Back"
        '3' -> "Star Wars Episode VI Return of the Jedi"
        '4' -> "Star Wars Episode I The Phantom Menace"
        '5' -> "Star Wars Episode II Attack of the Clones"
        '6' -> "Star Wars Episode III Revenge of the Sith"
        else -> null
    }
}