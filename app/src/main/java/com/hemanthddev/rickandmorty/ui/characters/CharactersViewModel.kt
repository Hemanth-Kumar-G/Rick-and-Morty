package com.hemanthddev.rickandmorty.ui.characters

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hemanthddev.rickandmorty.base.BaseViewModel
import com.hemanthddev.rickandmorty.data.repository.character.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.hemanthddev.rickandmorty.data.model.Character

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : BaseViewModel() {

    private lateinit var _charactersFlow: Flow<PagingData<Character>>
    val charactersFlow: Flow<PagingData<Character>>
        get() = _charactersFlow

    init {
        getAllCharacters()
    }

    private fun getAllCharacters() = launchPagingAsync({
        characterRepository.getAllCharacters().cachedIn(viewModelScope)
    }, {
        _charactersFlow = it
    })

}