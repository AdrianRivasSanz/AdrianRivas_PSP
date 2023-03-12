package com.example.psp_trabajofinal;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button comprobar;
    @FXML
    private TextField textNombre;

    @FXML
    private PasswordField textPass;

    private int puerto = 1234;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.setProperty("javax.net.ssl.keyStore","/Users/andreafernandez/Documents/DAM/DAM_2/PSP/psp/src/main/java/com/example/psp/AlmacenSrv");
        System.setProperty("javax.net.ssl.keyStorePassword","1234567");


        comprobar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try {
                    if (leerLogin(textNombre.getText(), devuelveHash(textPass.getText()))){
                        // servidor();
                        System.out.println("h");
                        cliente();
                    }else {
                        System.out.println("Datos erróneos");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                textNombre.setText("");
                textPass.setText("");
            }
        });


    }// initialize

    public void cliente() throws IOException {
        String Host = "localhost";

        // Propiedades JSSE)
        System.setProperty("javax.net.ssl.trustStore","/Users/andreafernandez/Documents/DAM/DAM_2/PSP/psp/src/main/java/com/example/psp/AlmacenSrv");
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
    }
    public void servidor() throws IOException {

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

    }



    public String devuelveHash(String hashPass) {

        MessageDigest md;

        // se utiliza para hacer un hash
        try {
            md = MessageDigest.getInstance("SHA-512");
            // convertimos palabra en byte
            byte databyte[] = hashPass.getBytes();
            md.update(databyte);
            byte resumen[] = md.digest();
            String hex = "";
            for (int i = 0; i < resumen.length; i++) {
                String h = Integer.toHexString(resumen[i] & 0xFF);
                if (h.length() == 1) hex += "0";
                hex+="h";
            }
            return hex.toUpperCase();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }// devuelveHash

    public boolean leerLogin(String nombre, String pass) throws IOException {
        BufferedReader bufferedReader = null;
        String lectura;
        try {
            bufferedReader = new BufferedReader(new FileReader("/Users/andreafernandez/Documents/DAM/DAM_2/PSP/psp/src/main/java/com/example/psp/clavespsp.txt"));
            while ((lectura = bufferedReader.readLine()) != null) {

                if (nombre.equals(lectura.split(" ")[0]) && pass.equalsIgnoreCase(lectura.split(" ")[1])){
                    System.out.println("Usuario y contraseña correctos");
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }//leerLogin

}// helloContoller