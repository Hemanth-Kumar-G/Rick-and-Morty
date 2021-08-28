package com.hemanthddev.rickandmorty.ui.characters.details

import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.hemanthddev.rickandmorty.R
import com.hemanthddev.rickandmorty.base.BaseFragment
import com.hemanthddev.rickandmorty.databinding.CharacterDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment<CharacterDetailFragmentBinding, CharacterDetailViewModel>() {

    private val characterDetailViewModel: CharacterDetailViewModel by viewModels()

    private val args: CharacterDetailFragmentArgs by navArgs()

    override val layoutId: Int
        get() = R.layout.character_detail_fragment

    override fun getVM(): CharacterDetailViewModel = characterDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun bindVM(binding: CharacterDetailFragmentBinding, vm: CharacterDetailViewModel) {
        with(binding) {
            character = args.characters
            ViewCompat.setTransitionName(binding.ivAvatar, "avatar_${args.characters.id}")
            ViewCompat.setTransitionName(binding.tvName, "name_${args.characters.id}")
            ViewCompat.setTransitionName(binding.tvStatus, "status_${args.characters.id}")
        }
    }
}