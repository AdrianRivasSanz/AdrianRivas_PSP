package com.example.psp_trabajofinal;

import java.io.*;
import javax.net.ssl.*;

public class Cliente  {
    public static void main(String[] args) throws Exception {
        String Host = "localhost";
        int puerto = 1234;//puerto remoto


        // Propiedades JSSE)
        System.setProperty("javax.net.ssl.trustStore","/Users/adrianrivas/IdeaProjects/AdrianRivas_PSP/Psp_TrabajoFinal/src/main/java/com/example/psp_trabajofinal/AlmacenSrv");
        System.setProperty("javax.net.ssl.trustStorePassword","1234567");

        System.out.println("PROGRAMA CLIENTE INICIADO....");

        SSLSocketFactory sfact = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket Cliente  = (SSLSocket) sfact.createSocket(Host, puerto);

        // CREO FLUJO DE SALIDA AL SERVIDOR
        DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());

        // ENVIO UN SALUDO AL SERVIDOR
        flujoSalida.writeUTF("Saludos al SERVIDOR DESDE EL CLIENTE");


        // CREO FLUJO DE ENTRADA AL SERVIDOR
        DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());

        // EL servidor ME ENVIA UN MENSAJE
        System.out.println("Recibiendo del SERVIDOR: \n\t" + flujoEntrada.readUTF());

        // CERRAR STREAMS Y SOCKETS
        flujoEntrada.close();
        flujoSalida.close();
        Cliente.close();
    }// main



}//..ClienteSSLv