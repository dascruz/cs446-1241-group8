package com.example.harmonic.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalTimerInstance::class], version = 1)
abstract class HarmonicDatabase : RoomDatabase() {
    abstract fun timerInstanceDao(): TimerInstanceDao
}