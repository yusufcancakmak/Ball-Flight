package com.ballflight6463.module

import com.ballflight6463.network.service.profile.ProfileRemoteDataSource
import com.ballflight6463.network.service.profile.ProfileRemoteDataSourceImpl
import com.ballflight6463.network.service.profile.ProfileService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun providesProfileRemoteDataSource(
        service: ProfileService
    ) : ProfileRemoteDataSource {
        return ProfileRemoteDataSourceImpl(service)
    }
}