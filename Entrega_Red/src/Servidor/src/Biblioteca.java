import java.io.IOException;
import java.net.ServerSocket;

public class Biblioteca {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        //arrancado el servidor

        try {
            serverSocket = new ServerSocket(2222);
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

