package com.example.entrega_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.entrega_final.adapters.LibrosAdapter
import com.example.entrega_final.databinding.ActivityHiloBinding
import com.example.entrega_final.dialogs.Dialogo
import com.example.entrega_final.model.Libros


class HiloActivity : AppCompatActivity(), Dialogo.OnGeneroListener {

    private lateinit var binding: ActivityHiloBinding

    private lateinit var generoLibros: String
    //CREO UNA VARIABLE PARA CONECTAR CON EL ADAPTADOR
    private lateinit var adapterlibros: LibrosAdapter
    //CREO UN ARRAY TIPO LIBROS PARA GUARDAR LOS LIBROS
    private lateinit var libreria: ArrayList<Libros>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHiloBinding.inflate(layoutInflater)
        setContentView(binding.root)
        acciones()
        instancias()
    }

    private fun instancias() {
        libreria = ArrayList();
        libreria.add(Libros("El Juego De Los Crimenes", "novela_negra", R.drawable.eljuegodeloscrimenes))
        libreria.add(Libros("La Metamorfosis Infinita", "novela_negra", R.drawable.lametamorfosisinfinita))
        libreria.add(Libros("Hijos De La Fabula", "novela_contemporanea", R.drawable.hijosdelafabula))
        libreria.add(Libros("La Secta", "novela_negra", R.drawable.lasecta))
        libreria.add(Libros("Haunting Adeline", "romantica", R.drawable.hauntingadeline))
        libreria.add(Libros("La Voz De Los Valientes", "novela_contemporanea", R.drawable.lavozdelosvalientes))
        libreria.add(Libros("Telefono Negro", "terror", R.drawable.telefononegro))
        libreria.add(Libros("Castillos de Fuego", "novela_contemporanea", R.drawable.castillosdefuego))
        libreria.add(Libros("Un Cuento Perfecto", "romantica", R.drawable.uncuentoperfecto))
        libreria.add(Libros("El Libro Negro De Las Horas", "novela_negra", R.drawable.ellibronegrodelashoras))
        libreria.add(Libros("El Caso Bramard", "novela_negra", R.drawable.elcasobramard))
        libreria.add(Libros("Harry Potter Y La Piedra Filosofal", "fantasia", R.drawable.harrypotterylapiedrafilosofal))
        adapterlibros = LibrosAdapter(this, libreria)
        binding.recyclerLibros.adapter = adapterlibros
        binding.recyclerLibros.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    //FUNCION DEL BOTON BUSCAR
    private fun acciones() {
        binding.botonBuscar.setOnClickListener {
            Dialogo().show(supportFragmentManager, "")
        }
    }

    override fun onGeneroSeleccionado(genero: String) {
        adapterlibros.cambiarLista(adapterlibros.libreria.filter {it.genero == generoLibros } as ArrayList<Libros>)
        binding.textConteo.setText(libreria.count())
    }

}