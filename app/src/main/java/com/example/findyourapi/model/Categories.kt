package com.example.findyourapi.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("count")
    @Expose
    val count:Int,

    @SerializedName("categories")
    @Expose
    val categories:List<String>
)
