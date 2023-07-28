package com.example.rickandmorty.dependency

import com.example.rickandmorty.ui.fragments.CharacterDetailFragment
import com.example.rickandmorty.ui.fragments.CharactersFragment
import com.example.rickandmorty.ui.fragments.LocationDetailFragment
import com.example.rickandmorty.ui.fragments.LocationsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(fragment: CharactersFragment)
    fun inject(fragment: CharacterDetailFragment)
    fun inject(fragment: LocationDetailFragment)
    fun inject(fragment: LocationsFragment)
}
