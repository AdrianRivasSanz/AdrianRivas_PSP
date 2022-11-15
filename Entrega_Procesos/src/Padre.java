import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Padre{

    public static void main(String[] args){
        String linea;
        Process hijo = null;

        try {
            hijo = new ProcessBuilder("java","C:\\Users\\Usuario DAM2\\Documents\\GitHub\\PSP\\Entrega_Procesos\\src\\Hijo.java").start();
            BufferedReader br =new BufferedReader(new InputStreamReader(hijo.getInputStream()));
            PrintStream ps = new PrintStream(hijo.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Hola, por favor");
            System.out.println("¿Cuantos libros tienes?");
            System.out.println("Elige un genero de tu biblioteca: Romantico, Terror, Negra, Fantastica, Gotica, Policiaca");

            linea = in.readLine();
            ps.println(linea);

            linea = br.readLine();
            System.out.println(linea);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
