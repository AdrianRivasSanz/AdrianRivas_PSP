package Servidor.src;

import java.io.Serializable;
import java.util.Collection;

public class Libro{

    //VARIABLES
    private String genero;

    //CONSTRUCTORES
    public Libro (String genero) {
        this.genero = genero;
    }

    public Libro (){
    }

    //TOSTRING
    @Override
    public String toString() {
        return "Libro {" +
                "genero =' + genero " +
                '}';
    }
}