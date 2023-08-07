package com.ballflight6463.repos

import com.ballflight6463.network.response.ProfileResponse

interface ProfileRepository {
    suspend fun fetchProfileInfo(): ProfileResponse

}