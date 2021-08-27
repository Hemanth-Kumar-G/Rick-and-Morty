package com.hemanthddev.rickandmorty.ui.characters

import androidx.fragment.app.viewModels
import com.hemanthddev.rickandmorty.R
import com.hemanthddev.rickandmorty.base.BaseFragment
import com.hemanthddev.rickandmorty.databinding.CharactersFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : BaseFragment<CharactersFragmentBinding, CharactersViewModel>() {

    private val viewModel: CharactersViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.characters_fragment

    override fun getVM(): CharactersViewModel = viewModel

    override fun bindVM(binding: CharactersFragmentBinding, vm: CharactersViewModel) {

    }

}