package com.ballflight6463.network.service.profile

import com.ballflight6463.network.response.ProfileResponse
import retrofit2.http.GET

interface ProfileService {

    @GET(com.ballflight6463.constans.NetworkConstans.Endpoint.Home.PROFILE)
    suspend fun fetchUserProfile() : ProfileResponse
}