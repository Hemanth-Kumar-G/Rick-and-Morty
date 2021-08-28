package com.hemanthddev.rickandmorty.ui.episodes

import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.hemanthddev.rickandmorty.R
import com.hemanthddev.rickandmorty.base.BaseFragment
import com.hemanthddev.rickandmorty.databinding.EpisodesFragmentBinding
import com.hemanthddev.rickandmorty.ui.adapter.EpisodeAdapter
import com.hemanthddev.rickandmorty.util.PagingLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

@AndroidEntryPoint
class EpisodesFragment : BaseFragment<EpisodesFragmentBinding, EpisodesViewModel>() {

    @Inject
    lateinit var episodeAdapter: EpisodeAdapter

    private val viewModel: EpisodesViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.episodes_fragment

    override fun getVM(): EpisodesViewModel = viewModel

    override fun bindVM(binding: EpisodesFragmentBinding, vm: EpisodesViewModel) = with(binding) {
        with(episodeAdapter) {
            swipeRefresh.setOnRefreshListener { refresh() }
            rvEpisodes.adapter = withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(this),
                footer = PagingLoadStateAdapter(this)
            )
            with(vm) {
                launchOnLifecycleScope {
                    episodesFlow.collectLatest { submitData(it) }
                }
                launchOnLifecycleScope {
                    loadStateFlow.collectLatest {
                        swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
                    }
                }
                launchOnLifecycleScope {
                    loadStateFlow.distinctUntilChangedBy { it.refresh }
                        .filter { it.refresh is LoadState.NotLoading }
                        .collect { rvEpisodes.scrollToPosition(0) }
                }
            }
        }
    }
}