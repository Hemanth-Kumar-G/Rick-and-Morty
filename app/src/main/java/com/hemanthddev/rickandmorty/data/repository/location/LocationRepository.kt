package com.hemanthddev.rickandmorty.data.repository.location

import androidx.paging.PagingData
import com.hemanthddev.rickandmorty.data.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getAllLocations(): Flow<PagingData<Location>>
}