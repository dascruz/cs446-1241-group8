package com.example.harmonic.services.module

import com.example.harmonic.services.SharingService
import com.example.harmonic.services.impl.SharingServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds abstract fun provideSharingService(impl: SharingServiceImpl): SharingService
}