import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Hilo extends Thread{

    private Socket cliente = null;


    String museoRecibido;
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
    Libro libro6 = new Libro("Romantico");
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
    Libro libro18 = new Libro("Romantico");
    Libro libro19 = new Libro("Policiaca");
    Libro libro20 = new Libro("Policiaca");
    Libro libro21 = new Libro("Romantico");
    Libro libro22 = new Libro("Romantico");
    Libro libro23 = new Libro("Romantico");
    Libro libro24 = new Libro("Fantastica");
    Libro libro25 = new Libro("Fantastica");


    public Hilo(Socket param){
        this.cliente = param;
    }

    public void run(){


        librosRomanticos.add(libro1);
        librosRomanticos.add(libro2);
        librosRomanticos.add(libro3);
        librosRomanticos.add(libro4);
        librosRomanticos.add(libro5);
        librosRomanticos.add(libro6);
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
        librosRomanticos.add(libro18);
        librosPoliciaca.add(libro19);
        librosPoliciaca.add(libro20);
        librosRomanticos.add(libro21);
        librosRomanticos.add(libro22);
        librosRomanticos.add(libro23);
        librosFantastica.add(libro24);
        librosFantastica.add(libro25);


        try {

            PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader((cliente.getInputStream())));
            String inputLine = in.readLine();



            museoRecibido = inputLine;
            int obraExpuesta = (int)(Math.random()*4);

            if(museoRecibido.equalsIgnoreCase("MUSEO DEL PRADO")){
                out.println(obrasPrado.get(obraExpuesta));
            }else if (museoRecibido.equalsIgnoreCase("NATIONAL GALLERY")) {
                out.println(obrasNatGal.get(obraExpuesta));
            }else if (museoRecibido.equalsIgnoreCase("MUSEO DEL LOUVRE")) {
                out.println(obrasLouvre.get(obraExpuesta));
            }



            System.out.println("Recibido: " + inputLine);
            //el servidor contesta
            out.println("Opción no válida");
            out.close();
            in.close();
            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}