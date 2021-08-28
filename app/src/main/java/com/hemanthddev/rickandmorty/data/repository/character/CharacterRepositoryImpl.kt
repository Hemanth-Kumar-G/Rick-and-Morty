package com.hemanthddev.rickandmorty.data.repository.character

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hemanthddev.rickandmorty.data.model.Character
import com.hemanthddev.rickandmorty.data.paging.dataSource.CharactersPagingDataSource
import com.hemanthddev.rickandmorty.data.service.CharacterApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val apiService: CharacterApi
) : CharacterRepository {
    override suspend fun getAllCharacters(): Flow<PagingData<Character>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { CharactersPagingDataSource(apiService) }
    ).flow


        override suspend fun getSingleCharacter(id: Int) {
            TODO("Not yet implemented")
        }

    override suspend fun getMultipleCharacters(ids: List<Int>) {
        TODO("Not yet implemented")
    }

    override suspend fun filterCharacters(
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    ) {
        TODO("Not yet implemented")
    }
}