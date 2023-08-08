package ru.binnyatoff.starwars.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.binnyatoff.starwars.data.network.models.TypeOfResult

@Entity(tableName = "result_main")
data class ResultMainDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "type")
    val type: TypeOfResult,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "diameter")
    val diameter: String?,
    @ColumnInfo(name = "population")
    val population: String?,
    @ColumnInfo(name = "gender")
    val gender: String?,
    @ColumnInfo(name = "starships")
    val starships: Int?,
    @ColumnInfo(name = "films")
    val films: String?,
    @ColumnInfo(name = "manufacturer")
    val manufacturer: String?,
    @ColumnInfo(name = "model")
    val model: String?,
    @ColumnInfo(name = "passengers")
    val passengers: String?,
)