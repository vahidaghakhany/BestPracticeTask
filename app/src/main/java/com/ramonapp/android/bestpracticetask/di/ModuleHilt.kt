package com.ramonapp.android.bestpracticetask.di

import com.ramonapp.android.bestpracticetask.domain.di.DispatcherDefault
import com.ramonapp.android.bestpracticetask.domain.di.DispatcherIO
import com.ramonapp.android.bestpracticetask.domain.di.DispatcherUnconfined
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object ModuleHilt {

    @Provides
    @DispatcherIO
    fun provideDispatcherIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @DispatcherDefault
    fun provideDispatcherDefault(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    @Provides
    @DispatcherUnconfined
    fun provideDispatcherUnconfined(): CoroutineDispatcher {
        return Dispatchers.Unconfined
    }
}