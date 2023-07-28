package com.example.rickandmorty.repository.data_models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResultEpisode(
    @SerializedName("info")
    @Expose
    val resultInfo: Info,
    @SerializedName("results")
    @Expose
    val episodeDescriptionsList: List<EpisodeDescription>
)


data class EpisodeDescription(
    @SerializedName("id")
    @Expose
    val episodeId: Int,

    @SerializedName("name")
    @Expose
    val episodeName: String,

    @SerializedName("air_date")
    @Expose
    val episodeAirDate: String,

    @SerializedName("episode")
    @Expose
    val episode: String,

    @SerializedName("characters")
    @Expose
    val episodeCharacters: List<String>,

    @SerializedName("url")
    @Expose
    val episodeUrl: String,

    @SerializedName("created")
    @Expose
    val episodeCreated: String
)