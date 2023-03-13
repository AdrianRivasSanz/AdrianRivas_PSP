import java.io.Serializable;

public class Usuario implements Serializable {

    //VARIABLES
    private String nombre, password;

    //CONSTRUCTORES
    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    public Usuario() {

    }

    //GETTERS Y SETTERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}