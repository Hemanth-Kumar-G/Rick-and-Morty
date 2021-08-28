package com.hemanthddev.rickandmorty.ui.characters

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.hemanthddev.rickandmorty.R
import com.hemanthddev.rickandmorty.base.BaseFragment
import com.hemanthddev.rickandmorty.databinding.CharactersFragmentBinding
import com.hemanthddev.rickandmorty.ui.adapter.CharacterAdapter
import com.hemanthddev.rickandmorty.util.PagingLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

private const val TAG = "CharactersFragment"

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
            }
        }

    }
}