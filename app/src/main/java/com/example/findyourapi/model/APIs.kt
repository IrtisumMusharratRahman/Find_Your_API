package com.example.findyourapi.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class APIs(
    @SerializedName("count")
    @Expose
    val count:Int,

    @SerializedName("entries")
    @Expose
    val entries:List<Entrie>
)
