package ru.binnyatoff.starwars.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(resultMainDTO: ResultMainDTO)

    @Query("SELECT * FROM result_main ")
    suspend fun selectResult(): List<ResultMainDTO>

    @Delete
    suspend fun deleteResult(resultMainDTO: ResultMainDTO)

}