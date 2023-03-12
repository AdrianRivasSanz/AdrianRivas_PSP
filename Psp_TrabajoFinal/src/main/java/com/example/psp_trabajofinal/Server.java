package com.example.psp_trabajofinal;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.*;

public class Server {
    public static void main(String[] arg) throws IOException {
        int puerto = 5556;

        System.setProperty("javax.net.ssl.keyStore","/Users/andreafernandez/Documents/DAM/DAM_2/PSP/psp/src/main/java/com/example/psp/AlmacenSrv");
        System.setProperty("javax.net.ssl.keyStorePassword","1234567");

        SSLServerSocketFactory sfact = (SSLServerSocketFactory) SSLServerSocketFactory
                .getDefault();
        SSLServerSocket servidorSSL = (SSLServerSocket) sfact
                .createServerSocket(puerto);
        SSLSocket clienteConectado = null;
        DataInputStream flujoEntrada = null;//FLUJO DE ENTRADA DE CLIENTE
        DataOutputStream flujoSalida = null;//FLUJO DE SALIDA AL CLIENTE

        System.out.println("Esperando al cliente ");
        clienteConectado = (SSLSocket) servidorSSL.accept();
        flujoEntrada = new DataInputStream(clienteConectado.getInputStream());

        // EL CLIENTE ME ENVIA UN MENSAJE
        System.out.println("Recibiendo del CLIENTE: " + " \n\t"
                + flujoEntrada.readUTF());

        flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());

        // ENVIO UN SALUDO AL CLIENTE
        flujoSalida.writeUTF("Saludos al cliente del servidor");


        // CERRAR STREAMS Y SOCKETS
        flujoEntrada.close();
        flujoSalida.close();
        clienteConectado.close();
        servidorSSL.close();

    }// main

}
