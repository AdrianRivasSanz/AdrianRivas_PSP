package com.example.entrega_final.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.entrega_final.R

class Dialogo: DialogFragment() {

    private lateinit var editGenero : EditText
    private lateinit var botonAceptar : Button
    private lateinit var listener: OnGeneroListener

    private lateinit var vista : View

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnGeneroListener;
        vista = LayoutInflater.from(context).inflate(R.layout.dialogo_item,null)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = AlertDialog.Builder(requireContext())
        builder.setView(vista)
        return builder.create()
    }

    override fun onStart() {
        super.onStart()
        editGenero = vista.findViewById(R.id.edit_dialogo_libros)
        botonAceptar = vista.findViewById(R.id.boton_dialogo_aceptar)
    }

    override fun onResume() {
        super.onResume()
        botonAceptar.setOnClickListener {
            listener.onGeneroSeleccionado(editGenero.toString())
            dismiss()
        }
    }

    interface OnGeneroListener{
        fun onGeneroSeleccionado(marca:String)
    }
}