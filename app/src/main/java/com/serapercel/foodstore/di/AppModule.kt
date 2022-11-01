package com.serapercel.foodstore.di

import com.serapercel.foodstore.data.datasource.FoodDatasource
import com.serapercel.foodstore.data.repo.FoodRepository
import com.serapercel.foodstore.retrofit.ApiUtils
import com.serapercel.foodstore.retrofit.FoodDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideFoodRepository(fds: FoodDatasource): FoodRepository {
        return FoodRepository(fds)
    }

    @Provides
    @Singleton
    fun provideFoodDataSource(fdao: FoodDAO): FoodDatasource {
        return FoodDatasource(fdao)
    }

    @Provides
    @Singleton
    fun provideFoodDAO(): FoodDAO {
        return ApiUtils.getFoodDao()
    }
}