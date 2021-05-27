package com.example.viagens.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.viagens.R
import com.example.viagens.adapter.GaleriaFotosDestinoAdapter
import com.example.viagens.api.FotoCall
import com.example.viagens.api.RetrofitApi
import com.example.viagens.model.DestinosRecentes
import com.example.viagens.model.Foto
import kotlinx.android.synthetic.main.activity_detalhe_destino.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class DetalheDestinoActivity : AppCompatActivity() {

    lateinit var ivFotoDestino: ImageView
    lateinit var tvLocal: TextView
    lateinit var tvValor: TextView
    lateinit var tvTextoDescricao: TextView
    lateinit var rvGaleriaFotosDestino: RecyclerView
    lateinit var galeriaFotosDestinoAdapter: GaleriaFotosDestinoAdapter
    lateinit var destinoRecente: DestinosRecentes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_destino)

        carregarDados()
        carregarListaDeFotos()
    }

    private fun carregarListaDeFotos() {

        rvGaleriaFotosDestino = findViewById(R.id.tv_galeria_de_fotos_destino)
        rvGaleriaFotosDestino.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        galeriaFotosDestinoAdapter = GaleriaFotosDestinoAdapter(this)
        rvGaleriaFotosDestino.adapter = galeriaFotosDestinoAdapter


        // Lista de fotos que preencherão a RecyclerView
        var fotos: List<Foto> = emptyList()

        // Instanciar o Retrofit
        val retrofit = RetrofitApi.getRetrofit()

        // Fazer Chamada para a Interface
        val fotosCall = retrofit.create(FotoCall::class.java)
        val call = fotosCall.getFotosDestino(destinoRecente.id)

        // Executar a requisição para a API
        call.enqueue(object : Callback<List<Foto>> {

            override fun onFailure(call: Call<List<Foto>>, t: Throwable) {
                Toast.makeText(this@DetalheDestinoActivity, "Ops... Falhou!!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Foto>>, response: Response<List<Foto>>) {
                fotos = response.body()!!
                galeriaFotosDestinoAdapter.updateListaDeFotos(fotos)
            }

        })

    }

    private fun carregarDados() {
        ivFotoDestino = findViewById(R.id.iv_destino)
        tvLocal = findViewById(R.id.tv_local)
        tvValor = findViewById(R.id.tv_valor)
        tvTextoDescricao = findViewById(R.id.tv_texto_descricao)

         destinoRecente =
            intent.getSerializableExtra("destino") as DestinosRecentes

        tvLocal.text = destinoRecente.nome
        tvValor.text = destinoRecente.valor.toString()
        tvTextoDescricao.text = destinoRecente.descricao

        Glide.with(this).load(destinoRecente.urlFoto).into(ivFotoDestino)
    }
}