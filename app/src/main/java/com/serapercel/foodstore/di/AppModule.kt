package com.serapercel.foodstore.di

import com.serapercel.foodstore.data.datasource.FoodDatasource
import com.serapercel.foodstore.data.repo.FoodRepository
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
    fun provideFoodDataSource(): FoodDatasource {
        return FoodDatasource()
    }
}