import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Padre extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println((i+1));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

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
        Hijo hijo1 = new Hijo();
        Padre padre1 = new Padre();
        hijo1.start();
        padre1.start();
        try {
            padre1.join();
        } catch
    }
}
