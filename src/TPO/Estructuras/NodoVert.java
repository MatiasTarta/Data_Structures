package TPO.Estructuras;

public class NodoVert {
    Object elemento;
    NodoVert sigVertice;
    NodoAdy primerAdy;

    public NodoVert(Object elem,NodoVert siguiente){
        elemento=elem;
        sigVertice=siguiente;
        primerAdy=null;
    }

    public Object getElemento(){return elemento;}

    public void setElemento(Object e){elemento=e;}

    public NodoVert getSigVertice() {
        return sigVertice;
    }
    
    public void setSigVertice(NodoVert sigVertice) {
        this.sigVertice = sigVertice;
    }
    
    public NodoAdy getPrimerAdy() {
        return primerAdy;
    }
    
    public void setPrimerAdy(NodoAdy primerAdy) {
        this.primerAdy = primerAdy;
    }
}
