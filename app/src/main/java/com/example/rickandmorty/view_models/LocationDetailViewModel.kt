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
class LocationDetailViewModel  @Inject constructor(private val repository: RickAndMortyRepository):ViewModel(){

    private val _locationDetailData = MutableLiveData<LocationDescription>()
    private val _charactersData = MutableLiveData<List<Character>?>()

    val locationDetailData: LiveData<LocationDescription>
        get() = _locationDetailData

    val charactersData
    : LiveData<List<Character>?>
        get() = _charactersData

    fun loadLocation(id: Int) {
        viewModelScope.launch {
            repository.getLocationDescription(id).map { locationDescription ->
                _locationDetailData.value = locationDescription
                val list = locationDescription.locationResidents.asSequence()
                    .take(5)
                    .map { it.last() }
                    .filter { char -> char.isDigit() }
                    .map { it.digitToInt() }
                    .toList()
                repository.getCharactersArray(list).map { characters->
                    _charactersData.value = characters
                }
            }
        }
    }
}