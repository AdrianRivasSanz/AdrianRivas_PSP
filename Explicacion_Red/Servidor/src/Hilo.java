import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.Buffer;

public class Hilo extends Thread{
    private Socket cliente = null;

    public Hilo(Socket param)
    {
        this.cliente = param;
    }

    public void run()
    {
        try {
            PrintWriter out = new PrintWriter(cliente.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            String inputline = in.readLine();

            System.out.println("recibido: " + inputline);

            out.println("Nos ha llegado el mensaje");


            out.close();
            in.close();
            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}




