package com.paf.unsplashsample.repo

import com.paf.unsplashsample.api.ApiClient
import com.paf.unsplashsample.model.PhotosResponse
import com.paf.unsplashsample.network.NetworkResult
import com.paf.unsplashsample.util.safeApiCall

class MainRepo(private val apiClient: ApiClient) {

    suspend fun getPhotos(page: Int = 1): NetworkResult<List<PhotosResponse>> {
        var result: NetworkResult<List<PhotosResponse>>? = null

        safeApiCall({ apiClient.getPhotos(page) },
            { result = it },
            { result = it }
        )

        return result!!
    }
}