package com.roynaldi19.kalkulator.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [History::class], version = 1)

abstract class HistoryDataBase : RoomDatabase(){

    abstract fun historyDao() : HistoryDao

    companion object {

        @Volatile private var instance : HistoryDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            HistoryDataBase::class.java,
            "history005.db"
        ).build()

    }
}