package com.example.entrega_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.entrega_final.databinding.ActivityMainBinding
import com.example.entrega_final.model.Usuario
import java.io.BufferedOutputStream
import java.io.PrintWriter
import java.net.Socket
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class MainActivity : AppCompatActivity() {

    //CREAMOS UNA VARIABLE DE USUARIO Y DE CONTRASEÑA
    lateinit var usuario: Usuario
    lateinit var passwordCifrado: Any
    //CREAMOS UN ARRAY DONDE PODER AÑADIR LOS NUEVOS USUARIOS
    private var arrayUsuarios = ArrayList<Usuario>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        acciones()
    }


    private fun acciones() {
        binding.botonRegistrar.setOnClickListener {
            class Enviar : Thread() {
                private lateinit var socket: Socket
                private lateinit var out: PrintWriter
                private lateinit var br: BufferedOutputStream

                override fun run() {
                    super.run()
                    socket = Socket("192.168.1.140", 4321)
                    out = PrintWriter(socket.getOutputStream(), true)
                    br = BufferedOutputStream(socket.getOutputStream())
                    out.write(usuario.nombre + " ")
                    out.write(passwordCifrado.toString())
                    out.close()
                    socket.close()
                    //CUANDO YA  HA CIFRADO LA PASSWORD LA MANDA AL FICHERO DE JAVA
                    //AÑADE LOS USUARIOS EN EL NUEVO ARRAY
                    if (binding.botonRegistrar.text == "Registrar") {
                        arrayUsuarios.add(usuario)
                    }
                }
            }
            var usuario = Enviar()
            usuario.start()
            guardar(binding.root)
        }
    }

    //CREAMOS LA FUNCION PARA PODER GUARDAR AL USUARIO
    fun guardar(view: View) {

        //1. REGISTRAR EL USUARIO CON SU NOMBRE Y LA CONTRASEÑA YA CIFRADA
        fun registrarUsuario() {
            passwordCifrado = metodohash(binding.editPassword.text.toString())!!;
            usuario = (Usuario(binding.editUsuario.text.toString(), passwordCifrado.toString()))

            binding.editUsuario.setText("")
            binding.editPassword.setText("")
        }

        //2. EL PROGRAMA EMPIEZA
        //SI EL USUARIO = X Y LA CONTRASEÑA = 9 SE PUEDE REGISTRAR
        if (binding.editUsuario.text.toString() == "x" && binding.editPassword.text?.toString() == "9") {
            registrarUsuario()

            binding.textRegistro.setText("Iniciando Sesion")
            binding.botonRegistrar.setText("Iniciar")
        }

        //SI EL USUARIO Y LA CONTRASEÑA NO SON CORRECTOS
        else if (binding.editUsuario.text.toString() == "1" && binding.editPassword.text?.toString() == "9") {

            binding.textRegistro.setText("Usuario Logueado")
            binding.botonRegistrar.setText("Login")
            registrarUsuario()
        }

        //SI NO LE PEDIRA QUE INTRODUZCA LOS DATOS PARA PODER REGISTRARSE
        else if (binding.editUsuario.text.toString() == "" && binding.editPassword.text.toString() == "") {
            Toast.makeText(this, "Introduzca los datos para poder registrarse", Toast.LENGTH_SHORT).show()
        }

        //CUANDO HAYA ENCONTRADO AL USUARIO EN EL FICHERO CAMBIARÁ DE PANTALLA
        else {
            arrayUsuarios.forEach {
                if (it.nombre == binding.editUsuario.text.toString()){
                    val intent= Intent(this, HiloActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            registrarUsuario()
        }
    }




    fun hexadecimal(resumen: ByteArray): String? {
        var hex = ""
        for (i in resumen.indices) {
            val h = Integer.toHexString(resumen[i].toInt() and 0xFF)
            if (h.length == 1) hex += "0"
            hex += h
        }
        return hex.uppercase(Locale.getDefault())
    }


    private fun metodohash(palabra: String): String? {
        val md: MessageDigest
        try {
            md = MessageDigest.getInstance("SHA512")
            val dataByte = palabra.toByteArray()
            md.update(dataByte)
            val resumen: ByteArray = md.digest()
            return hexadecimal(resumen)

        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }
}