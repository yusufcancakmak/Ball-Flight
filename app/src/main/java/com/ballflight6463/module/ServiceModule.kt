package com.ballflight6463.module

import com.ballflight6463.network.service.profile.ProfileService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun providesProfileService(retrofit: Retrofit) :ProfileService {
        return retrofit.create(ProfileService::class.java)
    }
}