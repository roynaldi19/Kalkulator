package com.roynaldi19.kalkulator.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class History(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val nilai1: Double,
    val nilai2: Double,
    val operator: String,
    val hasil: Double
)