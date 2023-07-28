package com.example.rickandmorty.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.repository.RickAndMortyRepository
import com.example.rickandmorty.repository.data_models.Character
import com.example.rickandmorty.repository.data_models.LocationDescription
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RickAndMortyViewModel @Inject constructor(private val repository: RickAndMortyRepository) :
    ViewModel() {

    private val _charactersData = MutableLiveData<ArrayList<Character>>()
    private val _locationsData = MutableLiveData<ArrayList<LocationDescription>>()

    val charactersData: LiveData<ArrayList<Character>>
        get() = _charactersData

    val locationsData: LiveData<ArrayList<LocationDescription>>
        get() = _locationsData

    fun loadCharacters() {
        viewModelScope.launch {
            val result = repository.getResultCharacters()
            result.map { resultCharacters ->
                _charactersData.value = resultCharacters.charactersList
            }
        }
    }

    fun loadLocations() {
        viewModelScope.launch {
            val result = repository.getResultLocations()
            result.map { resultLocations ->
                _locationsData.value = resultLocations.locations
            }
        }
    }
}