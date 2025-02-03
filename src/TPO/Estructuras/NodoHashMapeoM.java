package TPO.Estructuras;
import TPO.SistemaMudanzas.Cliente;;
public class NodoHashMapeoM {
    //tupla del dominio
    private String tipoDocumento;
    private int numeroDocumento;
    //rango y enlace
    private Object rango;
    private NodoHashMapeoM enlace;

    public NodoHashMapeoM(String tipoDocumento,int numeroDocumento, Object nuevo){
        this.tipoDocumento=tipoDocumento;
        this.numeroDocumento=numeroDocumento;
        rango= nuevo;
        enlace=null;
    }

    public void setRango(Object cliente){
        rango=cliente;
    }
    public Object getRango(){return rango;}

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
