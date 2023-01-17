import java.io.IOException;
import java.net.ServerSocket;

public class Biblioteca {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(4321);
            while(true)
            {
                new Hilo(serverSocket.accept()).start();
            }
            //serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

