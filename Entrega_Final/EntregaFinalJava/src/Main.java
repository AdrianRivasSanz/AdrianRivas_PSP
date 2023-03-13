import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //ESTABLECEMOS VARIABLES
        ServerSocket server;
        BufferedReader br = null;
        FileOutputStream fs;
        String lecturaFichero = "";
        FileWriter fw;
        BufferedWriter bwr;

        //CREAMOS UN FICHERO DE BASE DE DATOS Y ADJUNTAMOS LA RUTA EN LA QUE LO VAMOS A GUARDAR
        File ficheroBD = new File("/Users/adrianrivas/IdeaProjects/AdrianRivas_PSP/Entrega_Final/EntregaFinalJava/src/ficheroBD.txt");
        PrintWriter printWriter = null;


        //CREAMOS UN ARRAY PARA GUARDAR LOS USUARIO QUE HAYAMOS CREADO
        ArrayList<String> usuariosCreados = new ArrayList<>();
        ArrayList<Usuario> usuariosRecuperados = new ArrayList<>();
        Usuario usuarioRecuperado = null;
        Usuario usuario = null;

        try {
            System.out.println(InetAddress.getLocalHost());
            System.out.println("Puerto: " + 4321);
            System.out.println("Se está creando");
            System.out.println("Ingrese x y 9 para dejar de registrar usuarios");
            server = new ServerSocket(4321);
            BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroBD));
            bw.write("");
            bw.close();
            String nueve = " 0DC526D8C4FA04084F4B2A6433F4CD14664B93DF9FB8A9E00B77BA890B83704D24944C93CAA692B51085BB476F81852C27E793600F137AE3929018CD4C8F1A45";

            //EMPIEZA LA LECTURA DE FICHERO
            while (!lecturaFichero.equals('x' + nueve)) {
                String lectura = null;
                br = new BufferedReader(new FileReader(ficheroBD));
                while ((lectura = br.readLine()) != null) {
                    String[] separoNombrePassword = lectura.split(" ");
                    if (separoNombrePassword.length != 2) {
                        int conteo = 3;
                        String nombre = null;
                        String password = null;
                        for (String item : separoNombrePassword) {
                            if (conteo %2 != 0) {
                                nombre = item;
                                conteo++;
                            } else {
                                password = item;
                                conteo++;
                            }
                        }
                        usuarioRecuperado = new Usuario(nombre, password);
                        usuariosRecuperados.add(usuarioRecuperado);

                        // COMUNICACIÓN
                        // MANDAMOS EL HILO A ANDROID
                        for (Usuario usuarioDeFichero : usuariosRecuperados) {
                            System.out.println("Dentro del Arraylist " + usuarioDeFichero.getNombre());
                        }
                    }

                    else {
                        int cont = 0;
                        String nombre1 = null;
                        String password1 = null;

                        for (String it : separoNombrePassword) {
                            if (cont == 0) {
                                nombre1 = it;
                                cont++;
                            } else {
                                password1 = it;
                            }
                        }
                        //METO EL PRIMER USUARIO
                        usuarioRecuperado = new Usuario(nombre1, password1);
                        System.out.println("Usuario " + usuarioRecuperado.getNombre() + " introducido");
                        usuariosRecuperados.add(usuarioRecuperado);
                    }
                }//TERMINA LA LECTURA DE FICHERO Y SE EMPIEZA A ESCRIBIR

                //EMPEZAMOS LA ESCRITURA
                Socket cliente = server.accept();
                br = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                fw = new FileWriter(ficheroBD.getAbsoluteFile(), true);
                bwr = new BufferedWriter(fw);
                lecturaFichero = br.readLine();
                String[] nombreYApellido = lecturaFichero.split(" ");
                printWriter = new PrintWriter(ficheroBD);

                //INTRODUCIMOS EL USUARIO
                String usuarioCompleto = nombreYApellido[0] + " " + nombreYApellido[1];

                for (int i = 0; i < 1; i++) {
                    usuariosCreados.add(usuarioCompleto);
                }
                fw.write(String.valueOf(usuariosCreados));
                cliente.close();
                fw.close();
            }
            //BUSCA EL USUARIO PARA VER SI EXISTE YA
            System.out.println("Buscando el usuario");
            System.out.println("Ingresa x y 9 para terminar la busqueda");
            while (!lecturaFichero.equals(1 + nueve)) {
                Socket cliente = server.accept();
                br = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                fw = new FileWriter(ficheroBD.getAbsoluteFile(), true);
                lecturaFichero = br.readLine();
                String[] usuarioDos = lecturaFichero.split(" ");

                //SI EL USUARIO QUE ESTÁ GUARDADO EN EL FICHERO COINCIDE CON EL USUARIO QUE SE ACABA DE INTRODUCIR POR ESCRITURA
                //SE PODRÁ LOGUEAR EL USUARIO
                for (Usuario usuarioDeFichero : usuariosRecuperados) {
                    if (usuarioDos[0].equals(usuarioDeFichero.getNombre())) {
                        System.out.println(usuarioDos[0] + " coincide con " + usuarioDeFichero.getNombre());
                        System.out.println("Logando al usuario");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
