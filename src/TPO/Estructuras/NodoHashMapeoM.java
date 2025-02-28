package TPO.Estructuras;
public class NodoHashMapeoM {
    private Object dominio;
    private Object rango;
    private NodoHashMapeoM enlace;

    public NodoHashMapeoM(Object dominio, Object nuevo){
        this.dominio=dominio;
        rango= nuevo;
        enlace=null;
    }

    public void setDominio(Object dom){
        dominio=dom;
    }

    public Object getDominio(){
        return dominio;
    }

    public void setRango(Object cliente){
        rango=cliente;
    }
    public Object getRango(){return rango;}

    public NodoHashMapeoM getEnlace() {
        return enlace;
    }

    public void setEnlace(NodoHashMapeoM enlace) {
        this.enlace = enlace;
    }

  
    
}
