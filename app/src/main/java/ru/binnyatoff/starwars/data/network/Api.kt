package ru.binnyatoff.starwars.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.binnyatoff.starwars.data.network.models.people.People
import ru.binnyatoff.starwars.data.network.models.planets.Planets
import ru.binnyatoff.starwars.data.network.models.starships.Starships

interface Api {
    @GET("people/")
    suspend fun getPeople(@Query("search") searchPeopleName: String): Response<People>

    @GET("starships/")
    suspend fun getStarships(@Query("search") search: String): Response<Starships>

    @GET("planets/")
    suspend fun getPlanets(@Query("search") search: String): Response<Planets>
}