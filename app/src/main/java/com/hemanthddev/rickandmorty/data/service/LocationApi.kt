package com.hemanthddev.rickandmorty.data.service

import com.hemanthddev.rickandmorty.data.model.Location
import com.hemanthddev.rickandmorty.data.model.PagedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {
    @GET("location/")
    suspend fun getAllLocations(@Query("page") page: Int): Response<PagedResponse<Location>>

}