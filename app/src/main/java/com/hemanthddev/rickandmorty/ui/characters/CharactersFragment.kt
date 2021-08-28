package com.hemanthddev.rickandmorty.ui.characters

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.hemanthddev.rickandmorty.R
import com.hemanthddev.rickandmorty.base.BaseFragment
import com.hemanthddev.rickandmorty.data.model.Character
import com.hemanthddev.rickandmorty.databinding.CharactersFragmentBinding
import com.hemanthddev.rickandmorty.databinding.ItemCharacterBinding
import com.hemanthddev.rickandmorty.ui.adapter.CharacterAdapter
import com.hemanthddev.rickandmorty.util.PagingLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


@AndroidEntryPoint
class CharactersFragment : BaseFragment<CharactersFragmentBinding, CharactersViewModel>() {

    @Inject
    lateinit var characterAdapter: CharacterAdapter

    private val viewModel: CharactersViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.characters_fragment

    override fun getVM(): CharactersViewModel = viewModel

    override fun bindVM(binding: CharactersFragmentBinding, vm: CharactersViewModel) {
        with(binding) {
            with(characterAdapter) {
                rvCharacters.apply {
                    postponeEnterTransition()
                    viewTreeObserver.addOnPreDrawListener {
                        startPostponedEnterTransition()
                        true
                    }
                }
                rvCharacters.adapter = withLoadStateHeaderAndFooter(
                    header = PagingLoadStateAdapter(this),
                    footer = PagingLoadStateAdapter(this)
                )

                swipeRefresh.setOnRefreshListener { refresh() }

                with(vm) {
                    launchOnLifecycleScope {
                        charactersFlow.collectLatest { submitData(it) }
                    }
                    launchOnLifecycleScope {
                        loadStateFlow.collectLatest {
                            swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
                        }
                    }
                }
                onCharacterClickListener = { binding, character ->
                    onCharacterClicked(binding, character)
                }
            }
        }
    }

    private fun onCharacterClicked(binding: ItemCharacterBinding, character: Character) {
        val extras = FragmentNavigatorExtras(
            binding.ivAvatar to "avatar_${character.id}",
            binding.tvName to "name_${character.id}",
            binding.tvStatus to "status_${character.id}"
        )
        findNavController().navigate(
            CharactersFragmentDirections.actionCharactersToCharacterDetail(character),
            extras
        )
    }
}