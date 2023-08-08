package ru.binnyatoff.starwars.screens.favoriteScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.binnyatoff.starwars.R
import ru.binnyatoff.starwars.data.network.models.TypeOfResult
import ru.binnyatoff.starwars.screens.MainViewHolder

import ru.binnyatoff.starwars.screens.homeScreen.adapter.HomeScreenAdapter

class FavoriteScreenAdapter : HomeScreenAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return when (viewType) {
            TypeOfResult.PEOPLE.number -> FavoritePeopleViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rw_people, parent, false), delegate
            )

            TypeOfResult.STARSHIPS.number -> FavoriteStarshipsViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rw_starships, parent, false), delegate
            )

            TypeOfResult.PLANETS.number -> FavoritePlanetsViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rw_planets, parent, false), delegate
            )

            else -> {
                FavoritePeopleViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_rw_people, parent, false), delegate
                )
            }
        }

    }
}