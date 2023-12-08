package com.ramonapp.android.bestpracticetask.domain.di

import com.ramonapp.android.bestpracticetask.domain.usecase.DefaultGetAlbumCombinationStreamUseCase
import com.ramonapp.android.bestpracticetask.domain.usecase.DefaultGetAlbumCombinationUseCase
import com.ramonapp.android.bestpracticetask.domain.usecase.GetAlbumCombinationStreamUseCase
import com.ramonapp.android.bestpracticetask.domain.usecase.GetAlbumCombinationUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainBindModuleHilt {

    @Singleton
    @Binds
    internal abstract fun bindGetAlbumUseCase(
        defaultGetAlbumUseCase: DefaultGetAlbumCombinationUseCase
    ) : GetAlbumCombinationUseCase

    @Singleton
    @Binds
    internal abstract fun bindGetAlbumStreamUseCase(
        defaultGetAlbumStreamUseCase: DefaultGetAlbumCombinationStreamUseCase
    ) : GetAlbumCombinationStreamUseCase
}