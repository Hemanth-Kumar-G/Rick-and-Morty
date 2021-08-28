package com.hemanthddev.rickandmorty.di

import com.hemanthddev.rickandmorty.data.repository.character.CharacterRepository
import com.hemanthddev.rickandmorty.data.repository.character.CharacterRepositoryImpl
import com.hemanthddev.rickandmorty.data.repository.episode.EpisodeRepository
import com.hemanthddev.rickandmorty.data.repository.episode.EpisodeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCharacterRepository(characterRepositoryImpl: CharacterRepositoryImpl): CharacterRepository

    @Binds
    abstract fun bindEpisodeRepository(episodeRepositoryImpl: EpisodeRepositoryImpl): EpisodeRepository
}