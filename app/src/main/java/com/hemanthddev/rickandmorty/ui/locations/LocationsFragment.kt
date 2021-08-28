package com.hemanthddev.rickandmorty.ui.locations

import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.hemanthddev.rickandmorty.R
import com.hemanthddev.rickandmorty.base.BaseFragment
import com.hemanthddev.rickandmorty.databinding.LocationsFragmentBinding
import com.hemanthddev.rickandmorty.ui.adapter.LocationAdapter
import com.hemanthddev.rickandmorty.util.PagingLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

@AndroidEntryPoint
class LocationsFragment : BaseFragment<LocationsFragmentBinding, LocationsViewModel>() {

    @Inject
    lateinit var locationAdapter: LocationAdapter

    private val viewModel: LocationsViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.locations_fragment

    override fun getVM(): LocationsViewModel = viewModel

    override fun bindVM(binding: LocationsFragmentBinding, vm: LocationsViewModel) = with(binding) {
        with(locationAdapter) {
            swipeRefresh.setOnRefreshListener { refresh() }
            rvLocations.adapter = withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(this),
                footer = PagingLoadStateAdapter(this)
            )
            with(vm) {
                launchOnLifecycleScope {
                    locationsFlow.collectLatest { submitData(it) }
                }
                launchOnLifecycleScope {
                    loadStateFlow.collectLatest {
                        swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
                    }
                }
                launchOnLifecycleScope {
                    loadStateFlow.distinctUntilChangedBy { it.refresh }
                        .filter { it.refresh is LoadState.NotLoading }
                        .collect { rvLocations.scrollToPosition(0) }
                }
            }
        }
    }
}
