package com.diegochancafe.worldcup.di

import com.diegochancafe.worldcup.data.model.singleton.TokenSingleton
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module // -- Provide dependencies
@InstallIn(SingletonComponent::class)
object TokenModule {
    // --
    @Singleton
    @Provides
    fun provideTokenSingleton() = TokenSingleton
}