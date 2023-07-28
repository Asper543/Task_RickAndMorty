package com.example.rickandmorty.repository.data_models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResultLocations(
    @SerializedName("info")
    @Expose
    val resultInfo: Info,
    @SerializedName("results")
    @Expose
    val locations: ArrayList<LocationDescription>
)


data class LocationDescription(
    @SerializedName("id")
    @Expose
    val locationId: Int,

    @SerializedName("name")
    @Expose
    val locationName: String,

    @SerializedName("type")
    @Expose
    val locationType: String,

    @SerializedName("dimension")
    @Expose
    val locationDimension: String,

    @SerializedName("residents")
    @Expose
    val locationResidents: List<String>,

    @SerializedName("url")
    @Expose
    val locationUrl: String,

    @SerializedName("created")
    @Expose
    val locationCreated: String
)


