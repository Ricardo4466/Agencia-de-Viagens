package com.example.viagens.api

import com.example.viagens.model.Foto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FotoCall {

    @GET("destinos/{id}/fotos")
    fun getFotosDestino(@Path("id")id: Long): Call<List<Foto>>
}