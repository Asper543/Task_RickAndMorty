package com.example.rickandmorty.dependency

import com.example.rickandmorty.repository.RickAndMortyRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideRepository(): RickAndMortyRepository {
        return RickAndMortyRepository()
    }
}

