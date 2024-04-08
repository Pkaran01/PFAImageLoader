package com.paf.unsplashsample.api

import com.paf.unsplashsample.model.PhotosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("/photos")
    suspend fun getPhotos(
        @Query("page") page: Int = 1,
        @Query("client_id") clientId: String = "P-88Em4Ri9xe1hM3rzS9dRIDEs9KYOJiM_dhmDM99pM"
    ): List<PhotosResponse>
}