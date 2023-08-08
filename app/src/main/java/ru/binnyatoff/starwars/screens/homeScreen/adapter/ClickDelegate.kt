package ru.binnyatoff.starwars.screens.homeScreen.adapter

import ru.binnyatoff.starwars.data.database.ResultMainDTO
interface ClickDelegate {
    fun onClick(resultMain: ResultMainDTO)
}