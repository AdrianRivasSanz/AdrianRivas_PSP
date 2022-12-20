import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Cliente {
    public static void main(String[] args) {
        {
            Socket visitante;

            Scanner entrada = new Scanner(System.in);
            System.out.println("Escoge un museo para conocer alguna de sus obras principales de forma aleatoria: \n" +
                    "1. MUSEO DEL PRADO \n2. NATIONAL GALLERY \n3. MUSEO DEL LOUVRE \n");
            int seleccion = entrada.nextInt();
            String museo = "";

            switch (seleccion){
                case 1:
                    museo = "MUSEO DEL PRADO";
                    break;
                case 2:
                    museo = "NATIONAL GALLERY";
                    break;
                case 3:
                    museo = "MUSEO DEL LOUVRE";
                    break;

                case 4:
                    museo = "MUSEO DEL LOUVRE";
                    break;

                case 5:
                    museo = "MUSEO DEL LOUVRE";
                    break;
            }
            //fin museo

            try {
                visitante = new Socket("localhost",2222);
                PrintWriter out = new PrintWriter(visitante.getOutputStream(),true);
                BufferedReader in = new BufferedReader(new InputStreamReader(visitante.getInputStream()));

                out.println(museo);
                //out.println("hola papi");
                //leo lo que me contesta el server
                System.out.println(in.readLine());

                out.close();
                in.close();
                visitante.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}