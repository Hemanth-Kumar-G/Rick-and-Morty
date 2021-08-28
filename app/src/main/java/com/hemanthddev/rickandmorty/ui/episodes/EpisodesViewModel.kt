package com.hemanthddev.rickandmorty.ui.episodes

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hemanthddev.rickandmorty.base.BaseViewModel
import com.hemanthddev.rickandmorty.data.model.Episode
import com.hemanthddev.rickandmorty.data.repository.episode.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val episodeRepository: EpisodeRepository
) : BaseViewModel() {
    init {
        getAllEpisodes()
    }

    private lateinit var _episodesFlow: Flow<PagingData<Episode>>

    val episodesFlow: Flow<PagingData<Episode>>
        get() = _episodesFlow

    private fun getAllEpisodes() = launchPagingAsync({
        episodeRepository.getAllEpisodes().cachedIn(viewModelScope)
    }, {
        _episodesFlow = it
    })
}