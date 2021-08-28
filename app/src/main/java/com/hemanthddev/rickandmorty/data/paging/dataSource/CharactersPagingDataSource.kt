package com.hemanthddev.rickandmorty.data.paging.dataSource

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hemanthddev.rickandmorty.data.model.Character
import com.hemanthddev.rickandmorty.data.service.CharacterApi

class CharactersPagingDataSource(private val apiService: CharacterApi) :
    PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageNumber = params.key ?: 1

        return try {
            val response = apiService.getAllCharacters(page = pageNumber)
            val pagedResponse = response.body()
            val data = pagedResponse?.results

            var nextPageNumber: Int? = null

            if (pagedResponse?.pageInfo?.next != null) {
                val uri = Uri.parse(pagedResponse.pageInfo.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = null,
                nextKey = nextPageNumber
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}