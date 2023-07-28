package com.example.rickandmorty.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.repository.RickAndMortyRepository
import com.example.rickandmorty.repository.data_models.Character
import com.example.rickandmorty.repository.data_models.EpisodeDescription
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterDetailViewModel @Inject constructor(private val repository: RickAndMortyRepository) :
    ViewModel() {
    private val _characterDetailData = MutableLiveData<Character?>()
    private val _episodesData = MutableLiveData<List<EpisodeDescription>?>()

    val characterDetailData: LiveData<Character?>
        get() = _characterDetailData

    val episodesData: LiveData<List<EpisodeDescription>?>
        get() = _episodesData
    fun cleared(){
        _characterDetailData.value = null
        _episodesData.value = null
    }

    fun loadCharacter(id: Int) {
        viewModelScope.launch {
            repository.getCharacterById(id).map { character ->
                _characterDetailData.value = character
                val list = character.episode.asSequence()
                    .take(5)
                    .map { it.last() }
                    .filter { char -> char.isDigit() }
                    .map { it.digitToInt() }
                    .toList()
                repository.getEpisodeDescriptionList(list).map { episodes->
                _episodesData.value = episodes
                }
                joinAll()
            }
        }
    }

}