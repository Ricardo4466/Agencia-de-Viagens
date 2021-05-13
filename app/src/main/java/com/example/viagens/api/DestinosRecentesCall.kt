package com.example.viagens.api

import com.example.viagens.model.DestinosRecentes
import retrofit2.Call
import retrofit2.http.GET

interface DestinosRecentesCall {

    @GET("destinos/recentes")
    fun getDestinosRecentes() : Call<List<DestinosRecentes>>
}