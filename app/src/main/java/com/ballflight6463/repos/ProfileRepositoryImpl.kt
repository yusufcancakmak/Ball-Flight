package com.ballflight6463.repos

import com.ballflight6463.network.response.ProfileResponse
import com.ballflight6463.network.service.profile.ProfileRemoteDataSource
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProfileRemoteDataSource
) : ProfileRepository {

    override suspend fun fetchProfileInfo(): ProfileResponse {
        return remoteDataSource.fetchProfileInfo()
    }
}