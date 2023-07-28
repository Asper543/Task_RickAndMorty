package com.example.rickandmorty.repository

import com.example.rickandmorty.common.Try
import com.example.rickandmorty.common.doWithTry
import com.example.rickandmorty.repository.data_models.*
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://rickandmortyapi.com/api/"

@Singleton
class RickAndMortyRepository  {

    private val gson = GsonBuilder().setLenient().create()
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build().create(RickAndMortyApi::class.java)

    suspend fun getResultCharacters(): Try<ResultCharacters> =
        api.getResultCharacters().doWithTry()

    suspend fun getCharacterById(characterId: Int): Try<Character> =
        api.getCharacterById(characterId).doWithTry()

    suspend fun getCharactersArray(arrayCharacterId: List<Int>): Try<List<Character>> =
        api.getCharactersList(arrayCharacterId).doWithTry()

    suspend fun getResultLocations(): Try<ResultLocations> =
        api.getResultLocations().doWithTry()

    suspend fun getLocationDescription(locationId: Int): Try<LocationDescription> =
        api.getLocationDescription(locationId).doWithTry()

    suspend fun getEpisodeDescriptionList(arrayEpisodeId: List<Int>): Try<List<EpisodeDescription>> =
        api.getEpisodeDescriptionList(arrayEpisodeId).doWithTry()
}