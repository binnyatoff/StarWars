package ru.binnyatoff.starwars.screens.homeScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.binnyatoff.starwars.R
import ru.binnyatoff.starwars.data.database.ResultMainDTO
import ru.binnyatoff.starwars.data.network.models.TypeOfResult
import ru.binnyatoff.starwars.screens.MainViewHolder

open class HomeScreenAdapter : ListAdapter<ResultMainDTO, MainViewHolder>(HomeDiffUtil) {
    var delegate: ClickDelegate? = null

    open fun attachDelegate(delegate: ClickDelegate) {
        this.delegate = delegate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return when (viewType) {
            TypeOfResult.PEOPLE.number -> PeopleViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rw_people, parent, false), delegate
            )

            TypeOfResult.STARSHIPS.number -> StarshipsViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rw_starships, parent, false), delegate
            )

            TypeOfResult.PLANETS.number -> PlanetsViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rw_planets, parent, false), delegate
            )

            else -> {
                PeopleViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_rw_people, parent, false),delegate
                )
            }
        }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when (item.type) {
            TypeOfResult.PEOPLE -> TypeOfResult.PEOPLE.number
            TypeOfResult.STARSHIPS -> TypeOfResult.STARSHIPS.number
            TypeOfResult.PLANETS -> TypeOfResult.PLANETS.number
        }
    }
}

object HomeDiffUtil : DiffUtil.ItemCallback<ResultMainDTO>() {

    override fun areItemsTheSame(oldItem: ResultMainDTO, newItem: ResultMainDTO): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ResultMainDTO, newItem: ResultMainDTO): Boolean {
        return oldItem.name == newItem.name
    }
}