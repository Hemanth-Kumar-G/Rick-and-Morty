package com.hemanthddev.rickandmorty.ui.locations

import androidx.fragment.app.viewModels
import com.hemanthddev.rickandmorty.R
import com.hemanthddev.rickandmorty.base.BaseFragment
import com.hemanthddev.rickandmorty.databinding.LocationsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationsFragment : BaseFragment<LocationsFragmentBinding, LocationsViewModel>() {

    private val viewModel: LocationsViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.locations_fragment

    override fun getVM(): LocationsViewModel = viewModel

    override fun bindVM(binding: LocationsFragmentBinding, vm: LocationsViewModel) {

    }

}
