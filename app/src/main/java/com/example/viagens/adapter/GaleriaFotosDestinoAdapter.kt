package com.example.viagens.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.viagens.R
import com.example.viagens.model.Foto
import com.example.viagens.ui.DialogImgDetail

class GaleriaFotosDestinoAdapter(var context: Context) :
    RecyclerView.Adapter<GaleriaFotosDestinoAdapter.ViewHolder>() {

    private var listaDeFotos: List<Foto> = emptyList()

    fun updateListaDeFotos(lista: List<Foto>) {
        listaDeFotos = lista

        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.destino_fotos_layout, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaDeFotos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foto = listaDeFotos[position]
        Glide.with(context).load(foto.urlFoto).into(holder.imageFoto)

        holder.imageFoto.setOnClickListener {
            val dialog = DialogImgDetail()
            dialog.updateImageUrl(foto.urlFoto)
            dialog.show((context as AppCompatActivity).supportFragmentManager, "Qualquer coisa")

        }
    }

    //    inner Class
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageFoto = view.findViewById<ImageView>(R.id.image_foto)
    }
}