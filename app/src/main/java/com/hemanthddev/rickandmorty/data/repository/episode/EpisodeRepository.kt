package com.hemanthddev.rickandmorty.data.repository.episode

import androidx.paging.PagingData
import com.hemanthddev.rickandmorty.data.model.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {
    fun getAllEpisodes(): Flow<PagingData<Episode>>
}