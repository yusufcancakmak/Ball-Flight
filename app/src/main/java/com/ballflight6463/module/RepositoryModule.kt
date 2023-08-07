package com.ballflight6463.module

import com.ballflight6463.network.service.profile.ProfileRemoteDataSource
import com.ballflight6463.repos.ProfileRepository
import com.ballflight6463.repos.ProfileRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesProfileRepository(dataSource: ProfileRemoteDataSource) : ProfileRepository {
        return ProfileRepositoryImpl(dataSource)
    }
}