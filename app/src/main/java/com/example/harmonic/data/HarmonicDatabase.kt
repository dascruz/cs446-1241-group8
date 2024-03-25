package com.example.harmonic.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.harmonic.data.CounterJob.CounterJobDao
import com.example.harmonic.data.TimerInstance.LocalTimerInstance
import com.example.harmonic.data.TimerInstance.TimerInstanceDao
import com.example.harmonic.data.TimerJob.LocalTimerJob
import com.example.harmonic.data.CounterJob.LocalCounterJob
import com.example.harmonic.data.TimerJob.TimerJobDao

@Database(entities = [LocalTimerInstance::class, LocalTimerJob::class, LocalCounterJob::class], version = 2, exportSchema = false)
abstract class HarmonicDatabase : RoomDatabase() {
    abstract fun timerInstanceDao(): TimerInstanceDao
    abstract fun timerJobDao(): TimerJobDao

    abstract fun CounterJobDao(): CounterJobDao
}