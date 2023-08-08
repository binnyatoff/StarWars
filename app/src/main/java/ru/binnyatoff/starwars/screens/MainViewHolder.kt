package ru.binnyatoff.starwars.screens

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.binnyatoff.starwars.data.database.ResultMainDTO
import ru.binnyatoff.starwars.screens.homeScreen.adapter.ClickDelegate

abstract class MainViewHolder(itemView: View, clickDelegate: ClickDelegate?) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(resultMain: ResultMainDTO)
}