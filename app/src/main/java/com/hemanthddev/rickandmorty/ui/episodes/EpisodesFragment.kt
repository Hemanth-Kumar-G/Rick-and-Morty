package com.hemanthddev.rickandmorty.ui.episodes

import androidx.fragment.app.viewModels
import com.hemanthddev.rickandmorty.R
import com.hemanthddev.rickandmorty.base.BaseFragment
import com.hemanthddev.rickandmorty.databinding.EpisodesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodesFragment : BaseFragment<EpisodesFragmentBinding, EpisodesViewModel>() {

    private val viewModel: EpisodesViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.episodes_fragment

    override fun getVM(): EpisodesViewModel = viewModel

    override fun bindVM(binding: EpisodesFragmentBinding, vm: EpisodesViewModel) {

    }


}