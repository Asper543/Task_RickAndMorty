package com.example.rickandmorty.repository.data_models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

 class ResultCharacters(
     @SerializedName("info")
    @Expose
    val info: Info,
     @SerializedName("results")
    @Expose
    val charactersList: ArrayList<Character>
)

data class Info(
    @SerializedName("count")
    @Expose
    val count: Int,
    @SerializedName("pages")
    @Expose
    val pages: Int,
    @SerializedName("next")
    @Expose
    val nextPage: String,
    @SerializedName("prev")
    @Expose
    val prevPage: String?,
)

data class Character(
    @SerializedName("id")
    @Expose
    val characterId: Int,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("species")
    @Expose
    val species: String,

    @SerializedName("type")
    @Expose
    val type: String,

    @SerializedName("gender")
    @Expose
    val gender: String,

    @SerializedName("origin")
    @Expose
    val originInfo: LocationInfo,

    @SerializedName("location")
    @Expose
    val locationInfo: LocationInfo,

    @SerializedName("image")
    @Expose
    val image: String,

    @SerializedName("episode")
    @Expose
    val episode: List<String>,

    @SerializedName("url")
    @Expose
    val url: String,

    @SerializedName("created")
    @Expose
    val created: String
)

data class LocationInfo(
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("url")
    @Expose
    val url: String
)
