package com.ballflight6463.network.service.profile

import com.ballflight6463.network.response.ProfileResponse

interface ProfileRemoteDataSource {
    suspend fun fetchProfileInfo(): ProfileResponse
}