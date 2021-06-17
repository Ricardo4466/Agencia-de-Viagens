package com.example.viagens.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.viagens.R
import com.example.viagens.adapter.DestinoRecenteAdapter
import com.example.viagens.api.DestinosRecentesCall
import com.example.viagens.api.RetrofitApi
import com.example.viagens.model.DestinosRecentes
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var rvDestinosRecentes: RecyclerView
    lateinit var adapterDestinosRecentes: DestinoRecenteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDestinosRecentes = findViewById(R.id.rv_destinos_recentes)

        rvDestinosRecentes.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false)

        adapterDestinosRecentes = DestinoRecenteAdapter(this)

        rvDestinosRecentes.adapter = adapterDestinosRecentes

        carregarListaDestinosRecentes()


        exibirProfile()

        tv_sign_out.setOnClickListener {
            sigOut()
        }
    }


    private fun carregarListaDestinosRecentes(){

        var destinosRecentes: List<DestinosRecentes> = listOf<DestinosRecentes>()
        val retrofit = RetrofitApi.getRetrofit()
        val destinosRecentesCall = retrofit.create(DestinosRecentesCall::class.java)

        val call = destinosRecentesCall.getDestinosRecentes()

        call.enqueue(object : Callback<List<DestinosRecentes>> {
            override fun onFailure(call: Call<List<DestinosRecentes>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Ops Acho que ocorreu um problema.", Toast.LENGTH_SHORT).show()
                Log.e("ERRO_CONEXAO", t.message.toString())
            }

            override fun onResponse(
                call: Call<List<DestinosRecentes>>,
                response: Response<List<DestinosRecentes>>
            ) {
                destinosRecentes = response.body()!! // Double BANG!!
                adapterDestinosRecentes.updateListaRecente(destinosRecentes)
            }

        })

    }

    private fun sigOut(){

        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail().build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)

        googleSignInClient.signOut()

        finish()
    }

    private fun exibirProfile() {
        val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
        tv_display_name.text = dados.getString("display_name", "Nome do Usuario")

        val url = dados.getString("photo_url", null)
        Glide.with(this).load(url).into(iv_profile)
    }

}
