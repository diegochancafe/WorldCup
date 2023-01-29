package com.diegochancafe.worldcup.di

import android.content.Context
import androidx.room.Room
import com.diegochancafe.worldcup.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // -- Provide dependencies
@InstallIn(SingletonComponent::class)
object RoomModule {
    // --
    private const val DATABASE_NAME = "com.diegochancafe.worldcup"

    // --
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()

    // --
    @Singleton
    @Provides
    fun provideTeamDao(appDatabase: AppDatabase) = appDatabase.getTeamDao()

    @Singleton
    @Provides
    fun provideMatchDao(appDatabase: AppDatabase) = appDatabase.getMatchDao()
}