package com.example.harmonic.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.harmonic.data.RoutineInstance.LocalRoutineInstance
import com.example.harmonic.data.RoutineInstance.RoutineInstanceDao
import com.example.harmonic.data.RoutineJob.LocalRoutineJob
import com.example.harmonic.data.RoutineJob.RoutineJobDao
import com.example.harmonic.data.CounterJob.CounterJobDao
import com.example.harmonic.data.CounterJob.LocalCounterJob
import com.example.harmonic.data.TimerInstance.LocalTimerInstance
import com.example.harmonic.data.TimerInstance.TimerInstanceDao
import com.example.harmonic.data.TimerJob.LocalTimerJob
import com.example.harmonic.data.TimerJob.TimerJobDao
import com.example.harmonic.data.DecimalInstance.DecimalInstanceDao
import com.example.harmonic.data.DecimalJob.DecimalJobDao

@Database(
        entities = [LocalTimerInstance::class, LocalTimerJob::class, LocalRoutineInstance::class, LocalRoutineJob::class, LocalCounterJob::class],
        version = 2,
        exportSchema = false
)
abstract class HarmonicDatabase : RoomDatabase() {
    abstract fun timerInstanceDao(): TimerInstanceDao
    abstract fun timerJobDao(): TimerJobDao
    abstract fun routineInstanceDao(): RoutineInstanceDao
    abstract fun routineJobDao(): RoutineJobDao
    abstract fun CounterJobDao(): CounterJobDao

    abstract fun decimalInstanceDao(): DecimalInstanceDao
    abstract fun decimalJobDao(): DecimalJobDao
}
