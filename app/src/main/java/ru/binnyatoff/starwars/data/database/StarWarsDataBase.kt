package ru.binnyatoff.starwars.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ResultMainDTO::class], version = 1, exportSchema = false)
abstract class StarWarsDataBase:RoomDatabase() {
    abstract fun dao():Dao
}