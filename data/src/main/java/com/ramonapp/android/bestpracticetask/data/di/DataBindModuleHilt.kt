package com.ramonapp.android.bestpracticetask.data.di

import com.ramonapp.android.bestpracticetask.data.datasource.AlbumDataSource
import com.ramonapp.android.bestpracticetask.data.datasource.remote.RemoteAlbumDataSource
import com.ramonapp.android.bestpracticetask.data.repository.DefaultAlbumRepository
import com.ramonapp.android.bestpracticetask.domain.repository.AlbumRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindModuleHilt {

    @Singleton
    @Binds
    internal abstract fun bindAlbumRepository(
        defaultAlbumRepository: DefaultAlbumRepository
    ): AlbumRepository

    @Singleton
    @Binds
    internal abstract fun bindRemoteDataSource(
        remoteAlbumDataSource: RemoteAlbumDataSource
    ) : AlbumDataSource
}