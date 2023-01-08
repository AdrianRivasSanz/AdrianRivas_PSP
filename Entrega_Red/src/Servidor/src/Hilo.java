package Servidor.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Hilo extends Thread {

    private Socket cliente = null;

    ArrayList<Libro> librosRomanticos = new ArrayList<>();
    ArrayList<Libro> librosTerror = new ArrayList<>();
    ArrayList<Libro> librosNegra = new ArrayList<>();
    ArrayList<Libro> librosFantastica = new ArrayList<>();
    ArrayList<Libro> librosPoliciaca = new ArrayList<>();

    Libro libro1 = new Libro("Romantico");
    Libro libro2 = new Libro("Romantico");
    Libro libro3 = new Libro("Romantico");
    Libro libro4 = new Libro("Romantico");
    Libro libro5 = new Libro("Romantico");
    Libro libro6 = new Libro("Terror");
    Libro libro7 = new Libro("Terror");
    Libro libro8 = new Libro("Terror");
    Libro libro9 = new Libro("Terror");
    Libro libro10 = new Libro("Terror");
    Libro libro11 = new Libro("Negra");
    Libro libro12 = new Libro("Negra");
    Libro libro13 = new Libro("Negra");
    Libro libro14 = new Libro("Negra");
    Libro libro15 = new Libro("Negra");
    Libro libro16 = new Libro("Fantastica");
    Libro libro17 = new Libro("Fantastica");
    Libro libro18 = new Libro("Fantastica");
    Libro libro19 = new Libro("Fantastica");
    Libro libro20 = new Libro("Fantastica");
    Libro libro21 = new Libro("Policiaca");
    Libro libro22 = new Libro("Policiaca");
    Libro libro23 = new Libro("Policiaca");
    Libro libro24 = new Libro("Policiaca");
    Libro libro25 = new Libro("Policiaca");


    public Hilo(Socket param) {
        this.cliente = param;
    }

    public void run() {
        librosRomanticos.add(libro1);
        librosRomanticos.add(libro2);
        librosRomanticos.add(libro3);
        librosRomanticos.add(libro4);
        librosRomanticos.add(libro5);
        librosTerror.add(libro6);
        librosTerror.add(libro7);
        librosTerror.add(libro8);
        librosTerror.add(libro9);
        librosTerror.add(libro10);
        librosNegra.add(libro11);
        librosNegra.add(libro12);
        librosNegra.add(libro13);
        librosNegra.add(libro14);
        librosNegra.add(libro15);
        librosFantastica.add(libro16);
        librosFantastica.add(libro17);
        librosFantastica.add(libro18);
        librosFantastica.add(libro19);
        librosFantastica.add(libro20);
        librosPoliciaca.add(libro21);
        librosPoliciaca.add(libro22);
        librosPoliciaca.add(libro23);
        librosPoliciaca.add(libro24);
        librosPoliciaca.add(libro25);


        try {
            PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader((cliente.getInputStream())));
            String inputLine = in.readLine();
            String libroRecibido;
            libroRecibido = inputLine;
            int libroElegido = (int)(Math.random() * 6);

            if (libroRecibido.equalsIgnoreCase("Libro Romántico")) {
                out.println(librosRomanticos.get(libroElegido));
            } else if (libroRecibido.equalsIgnoreCase("Libro Terror")) {
                out.println(librosTerror.get(libroElegido));
            } else if (libroRecibido.equalsIgnoreCase("Libro Novela Negra")) {
                out.println(librosNegra.get(libroElegido));
            } else if (libroRecibido.equalsIgnoreCase("Libro Novela Fantastica")) {
                out.println(librosFantastica.get(libroElegido));
            } else if (libroRecibido.equalsIgnoreCase("Libro Novela Policiaca")) {
                out.println(librosPoliciaca.get(libroElegido));
            }


            System.out.println("Recibido: " + inputLine);
            out.println("La opción no es válida");
            out.close();
            in.close();
            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}