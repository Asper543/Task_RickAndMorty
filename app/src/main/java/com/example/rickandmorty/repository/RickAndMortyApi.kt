package com.example.rickandmorty.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.rickandmorty.*
import com.example.rickandmorty.repository.data_models.*

interface RickAndMortyApi {

    @GET("character")
    fun getResultCharacters(): Call<ResultCharacters>

    @GET("character/{characterId}")
    fun getCharacterById(@Path("characterId") characterId: Int): Call<Character>

    @GET("character/{arrayCharacterId}")
    fun getCharactersList(@Path("arrayCharacterId") arrayCharacterId: List<Int>): Call<List<Character>>

    @GET("location")
    fun getResultLocations(): Call<ResultLocations>

    @GET("location/{locationId}")
    fun getLocationDescription(@Path("locationId") locationId: Int): Call<LocationDescription>

    @GET("episode/{arrayEpisodeId}")
    fun getEpisodeDescriptionList(@Path("arrayEpisodeId") arrayEpisodeId: List<Int>): Call<List<EpisodeDescription>>
}
