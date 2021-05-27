package com.example.viagens.model

import com.google.gson.annotations.SerializedName

data class Foto (
    var id: Long,
    @SerializedName("url") var urlFoto: String,
    var destaque: Boolean

)
