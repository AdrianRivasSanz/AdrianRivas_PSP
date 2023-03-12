package com.example.entrega_final.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.entrega_final.R
import com.example.entrega_final.model.Libros

class LibrosAdapter(var contexto: Context, var libreria: ArrayList<Libros>) : RecyclerView.Adapter<LibrosAdapter.MyHolder>() {


    inner class MyHolder(vista: View) : RecyclerView.ViewHolder(vista) {
        lateinit var imagen : ImageView
        lateinit var genero : TextView
        lateinit var nombre : TextView
        init {
            imagen = vista.findViewById(R.id.imagen_libro)
            genero = vista.findViewById(R.id.genero_libro)
            nombre = vista.findViewById(R.id.nombre_libro)
        }
    }

    fun cambiarLista(listaNueva:ArrayList<Libros>){
        this.libreria = listaNueva;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var vista = LayoutInflater.from(contexto).inflate(R.layout.item_recycler,parent,false)
        return MyHolder(vista)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var libros = libreria[position];
        holder.imagen.setImageResource(libros.imagen)
        holder.genero.setText(libros.genero)
        holder.nombre.setText(libros.nombre)
    }

    override fun getItemCount(): Int {
        return libreria.size
    }

}