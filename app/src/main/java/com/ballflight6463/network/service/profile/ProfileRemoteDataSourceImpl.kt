package com.ballflight6463.network.service.profile

import com.ballflight6463.network.response.ProfileResponse

class ProfileRemoteDataSourceImpl(
    private val service: ProfileService
) : ProfileRemoteDataSource {
    override suspend fun fetchProfileInfo(): ProfileResponse {
        return service.fetchUserProfile()
    }
}