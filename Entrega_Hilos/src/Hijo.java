import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;

public class Hijo extends Thread{

    @Override
    public void run() {
        ArrayList<Libro> libros = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Libro libro26 = new Libro("Fantastica");
            Libro libro27 = new Libro("Fantastica");
            Libro libro28 = new Libro("Fantastica");
            Libro libro29 = new Libro("Fantastica");
            Libro libro30 = new Libro("Fantastica");
            libros.add(libro26);
            System.out.println(libro26.genero);
            libros.add(libro27);
            System.out.println(libro27.genero);
            libros.add(libro28);
            System.out.println(libro28.genero);
            libros.add(libro29);
            System.out.println(libro29.genero);
            libros.add(libro30);
            System.out.println(libro30.genero);

            System.out.println("");
        }
    }

    public static class Libro implements Serializable{
        String genero;
        public Libro (String genero){ this.genero = genero;}
        public String getGenero(){return genero;}
        public void setGenero(String genero){ this.genero = genero;}
    }

    public static void main(String[]args){
        ArrayList<Libro> libros = new ArrayList<>();

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
        Libro libro18 = new Libro("Gotica");
        Libro libro19 = new Libro("Policiaca");
        Libro libro20 = new Libro("Policiaca");
        Libro libro21 = new Libro("Romantico");
        Libro libro22 = new Libro("Romantico");
        Libro libro23 = new Libro("Romantico");
        Libro libro24 = new Libro("Gotica");
        Libro libro25 = new Libro("Fantastica");


        libros.add(libro1);
        libros.add(libro2);
        libros.add(libro3);
        libros.add(libro4);
        libros.add(libro5);
        libros.add(libro6);
        libros.add(libro7);
        libros.add(libro8);
        libros.add(libro9);
        libros.add(libro10);
        libros.add(libro11);
        libros.add(libro12);
        libros.add(libro13);
        libros.add(libro14);
        libros.add(libro15);
        libros.add(libro16);
        libros.add(libro17);
        libros.add(libro18);
        libros.add(libro19);
        libros.add(libro20);
        libros.add(libro21);
        libros.add(libro22);
        libros.add(libro23);
        libros.add(libro24);
        libros.add(libro25);

        String enviar;
        int numeroLibros = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        try{
            enviar = br.readLine();
            for(Libro item:libros){
                if(enviar.equalsIgnoreCase(item.getGenero())){
                    numeroLibros++;
                }
            }
            System.out.println("Tengo " +numeroLibros+ " de ese genero");

        }catch(IOException e){

            throw new RuntimeException(e);
        }
    }
}

