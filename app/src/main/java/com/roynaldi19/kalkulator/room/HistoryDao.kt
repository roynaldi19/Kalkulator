package com.roynaldi19.kalkulator.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.roynaldi19.kalkulator.room.History

@Dao
interface HistoryDao {
    @Insert
    suspend fun addHistory(note: History)

    @Query("SELECT * FROM history ORDER BY id DESC")
    fun getHistorys(): List<History>

    @Delete
    suspend fun deleteHistory(history: History)
}