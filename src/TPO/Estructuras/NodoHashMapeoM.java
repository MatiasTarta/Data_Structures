package TPO.Estructuras;
import TPO.SistemaMudanzas.Cliente;;
public class NodoHashMapeoM {
    //tupla del dominio
    private String tipoDocumento;
    private int numeroDocumento;
    //rango y enlace
    private Cliente rango;
    private NodoHashMapeoM enlace;

    public NodoHashMapeoM(String tipoDocumento,int numeroDocumento,String nombre,String apellido,int telefono,String email){
        this.tipoDocumento=tipoDocumento;
        this.numeroDocumento=numeroDocumento;
        rango= new Cliente(tipoDocumento, numeroDocumento, nombre, apellido,telefono,email);
        enlace=null;
    }

    public void setRango(Cliente clientela){
        rango=clientela;
    }
    public Cliente getRango(){return rango;}

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public NodoHashMapeoM getEnlace() {
        return enlace;
    }

    public void setEnlace(NodoHashMapeoM enlace) {
        this.enlace = enlace;
    }

  
    
}
