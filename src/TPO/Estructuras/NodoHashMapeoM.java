package TPO.Estructuras;
import lineales.dinamicas.Lista;
public class NodoHashMapeoM {
    //tupla del dominio
    private String tipoDocumento;
    private int numeroDocumento;
    //rango y enlace
    private Lista rango;
    private NodoHashMapeoM enlace;

    public NodoHashMapeoM(String nombre,String apellido,String email){
        rango= new Lista();
        rango.insertar(nombre,1);
        rango.insertar(apellido,2);
        rango.insertar(email,3);
        enlace=null;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombre() {
        return (rango.recuperar(1)).toString();
    }

    public String getApellido() {
        return (rango.recuperar(2)).toString();
    }

    public String getEmail() {
        return (rango.recuperar(3)).toString();
    }

    public void setNombre(String nombre) {
        rango.insertar(nombre,1);
    }

    public void setApellido(String apellido) {
        rango.insertar(apellido,2);
        
    }

    public void setEmail(String email) {
        rango.insertar(email,3);
    }

    public NodoHashMapeoM getEnlace() {
        return enlace;
    }

    public void setEnlace(NodoHashMapeoM enlace) {
        this.enlace = enlace;
    }

    public String toString() {
        return "NodoHashMapeoM{" +
                "tipoDocumento='" + tipoDocumento + '\'' +
                ", numeroDocumento=" + numeroDocumento +
                ", nombre='" + (rango.recuperar(1)).toString() + '\'' +
                ", apellido='" + (rango.recuperar(2)).toString() + '\'' +
                ", email='" + (rango.recuperar(3)).toString() + '\'' +
                '}';
    }
}
