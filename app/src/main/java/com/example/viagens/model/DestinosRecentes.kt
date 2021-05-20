package com.example.viagens.model

import java.io.Serializable

data class DestinosRecentes(
    var  id: Long = 0,
    var nome: String = "",
    var valor: Double = 0.0,
    var nomeCidade: String= "",
    var siglaEstadoSP: String = "",
    var urlFoto: String = "",
    var descricao: String = ""
):Serializable