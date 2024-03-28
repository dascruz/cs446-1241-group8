/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.harmonic.di

import android.content.Context
import androidx.room.Room
import com.example.harmonic.data.HarmonicDatabase
import com.example.harmonic.data.RoutineInstance.RoutineInstanceDao
import com.example.harmonic.data.RoutineJob.RoutineJobDao
import com.example.harmonic.data.TimerInstance.TimerInstanceDao
import com.example.harmonic.data.TimerJob.TimerJobDao
import com.example.harmonic.data.CounterJob.CounterJobDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): HarmonicDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            HarmonicDatabase::class.java,
            "Harmonic.db"
        ).build()
    }

    @Provides
    fun provideTimerInstanceDao(database: HarmonicDatabase) : TimerInstanceDao = database.timerInstanceDao()

    @Provides
    fun provideTimerJobDao(database: HarmonicDatabase) : TimerJobDao = database.timerJobDao()

    @Provides
    fun provideRoutineInstanceDao(database: HarmonicDatabase) : RoutineInstanceDao = database.routineInstanceDao()

    @Provides
    fun provideRoutineJobDao(database: HarmonicDatabase) : RoutineJobDao = database.routineJobDao()

    @Provides
    fun provideCounterJobDao(database: HarmonicDatabase) : CounterJobDao = database.CounterJobDao()
}