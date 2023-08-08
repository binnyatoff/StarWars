package ru.binnyatoff.starwars.screens.favoriteScreen.adapter

import android.view.View
import android.widget.Button
import android.widget.TextView
import ru.binnyatoff.starwars.R
import ru.binnyatoff.starwars.data.database.ResultMainDTO
import ru.binnyatoff.starwars.screens.MainViewHolder
import ru.binnyatoff.starwars.screens.homeScreen.adapter.ClickDelegate

class FavoritePeopleViewHolder(itemView: View, private val clickDelegate: ClickDelegate?) :
    MainViewHolder(itemView, clickDelegate) {
    override fun bind(resultMain: ResultMainDTO) {
        val peopleName = itemView.findViewById<TextView>(R.id.people_name)
        val gender = itemView.findViewById<TextView>(R.id.gender)
        val starships = itemView.findViewById<TextView>(R.id.starships)
        val films = itemView.findViewById<TextView>(R.id.films)
        val deleteInFavorite = itemView.findViewById<Button>(R.id.addToFavorite)

        peopleName.text = resultMain.name
        gender.text = resultMain.gender
        starships.text = resultMain.starships.toString()
        films.text = resultMain.films.toString()
        deleteInFavorite.text = "Delete"
        deleteInFavorite.setOnClickListener {
            clickDelegate?.onClick(resultMain)
        }
    }

}

class FavoritePlanetsViewHolder(itemView: View, private val clickDelegate: ClickDelegate?) :
    MainViewHolder(itemView, clickDelegate = null) {
    override fun bind(resultMain: ResultMainDTO) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val population = itemView.findViewById<TextView>(R.id.population)
        val diameter = itemView.findViewById<TextView>(R.id.diameter)
        val deleteInFavorite = itemView.findViewById<Button>(R.id.addToFavorite)

        name.text = resultMain.name
        population.text = resultMain.population
        diameter.text = resultMain.diameter
        deleteInFavorite.text = "Delete"
        deleteInFavorite.setOnClickListener {
            clickDelegate?.onClick(resultMain)
        }

    }
}

class FavoriteStarshipsViewHolder(itemView: View, private val clickDelegate: ClickDelegate?) :
    MainViewHolder(itemView, clickDelegate) {
    override fun bind(resultMain: ResultMainDTO) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val manufacturer = itemView.findViewById<TextView>(R.id.manufacturer)
        val model = itemView.findViewById<TextView>(R.id.model)
        val passengers = itemView.findViewById<TextView>(R.id.passengers)
        val deleteInFavorite = itemView.findViewById<Button>(R.id.addToFavorite)

        name.text = resultMain.name
        manufacturer.text = resultMain.manufacturer
        model.text = resultMain.model
        passengers.text = resultMain.passengers.toString()
        deleteInFavorite.text = "Delete"
        deleteInFavorite.setOnClickListener {
            clickDelegate?.onClick(resultMain)
        }
    }
}