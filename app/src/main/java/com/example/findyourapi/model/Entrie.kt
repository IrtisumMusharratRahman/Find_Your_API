package com.example.findyourapi.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Entrie(
    @SerializedName("API")
    @Expose
    val API:String,

    @SerializedName("Description")
    @Expose
    val Description:String,

    @SerializedName("Auth")
    @Expose
    val Auth:String,

    @SerializedName("HTTPS")
    @Expose
    val HTTPS:String,

    @SerializedName("Cors")
    @Expose
    val Cors:String,

    @SerializedName("Link")
    @Expose
    val Link:String,

    @SerializedName("Category")
    @Expose
    val Category:String
)
