import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {

        Socket cliente;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Elige un genero para que te salga un libro aleatorio: \n" +
                "1. Libro Romántico \n2. Libro Terror \n3. Libro Novela Negra \n4. Libro Novela Fantastica \n5. Libro Novela Policiaca");
        int seleccion = entrada.nextInt();
        String genero = "";

        switch (seleccion) {
            case 1:
                genero = "Libro Romántico";
                break;
            case 2:
                genero = "Libro Terror";
                break;
            case 3:
                genero = "Libro Novela Negra";
                break;

            case 4:
                genero = "Libro Novela Fantastica";
                break;

            case 5:
                genero = "Libro Novela Policiaca";
                break;
        }

        try {
            cliente = new Socket("localhost", 4321);
            PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            out.println(genero);
            System.out.println(in.readLine());
            out.close();
            in.close();
            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}