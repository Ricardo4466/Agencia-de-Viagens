package com.example.viagens.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.viagens.R

class DialogImgDetail : DialogFragment() {

    lateinit var image : ImageView
    lateinit var btnFechar : ImageButton

    private var imageUrl: String = ""

    fun updateImageUrl(imageUrl: String){
        this.imageUrl = imageUrl

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.customDialog)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_foto_datail, container, false)

        image = view.findViewById(R.id.image_foto)
        btnFechar = view.findViewById(R.id.imgbuttom_close)

        btnFechar.setOnClickListener {
            dismiss()
        }

        Glide.with(context!!).load(imageUrl).into(image)

        return view
    }
}