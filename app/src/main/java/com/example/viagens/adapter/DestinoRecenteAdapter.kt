package com.example.viagens.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.viagens.R
import com.example.viagens.model.DestinosRecentes

class DestinoRecenteAdapter(
    val listRecentes: List<DestinosRecentes>,
    val context: Context): RecyclerView.Adapter<DestinoRecenteAdapter.Holder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.card_recentes, parent, false)

        return  Holder(view)
    }

    override fun getItemCount(): Int {
        return listRecentes.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val destinosRecentes = listRecentes[position]

        holder.tvNomeDestino.text = destinosRecentes.nomeDestino
        holder.tvLocalidade.text = destinosRecentes.localidade
        holder.tvValor.text = destinosRecentes.descValor
    }





    // inner class
    class Holder(view: View): RecyclerView.ViewHolder(view){
        val tvNomeDestino = view.findViewById<TextView>(R.id.tv_nome_destino)
        val tvLocalidade = view.findViewById<TextView>(R.id.tv_localidade)
        val tvValor = view.findViewById<TextView>(R.id.tv_valor)
    }
}