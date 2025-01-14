package TPO.Estructuras;

public class NodoAVL {
    private Comparable elemento;
    private Object dato;
    private int altura;
    private NodoAVL izquierdo,derecho;

    public NodoAVL(Comparable elemento, int altura, NodoAVL izquierdo, NodoAVL derecho,Object dato){
        this.elemento=elemento;
        this.dato=dato;
        this.altura=0;
        this.izquierdo=izquierdo;
        this.derecho=derecho;
        recalcularAltura();//por si entran como parametro los izq o derechos
    }

    public Comparable  getElemento(){
        return elemento;
    }

    public void setElemento(Comparable elem){
        elemento=elem;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public int getAltura() {
        return altura;
    }
    
    public NodoAVL getIzquierdo(){
        return izquierdo;
    }

    public NodoAVL getDerecho(){
        return derecho;
    }
    public void setIzquierdo(NodoAVL izq){
        izquierdo=izq;
    }
    public void setDerecho(NodoAVL der){
        derecho=der;
    }

    public void recalcularAltura() {
        //actualiza la altura para ver si es nescesario rebalancear o no
        int izq = -1, der = -1;
        if (this.izquierdo != null) {
            izq = this.izquierdo.altura;
        }
        if (this.derecho != null) {
            der = this.derecho.altura;
        }
        this.altura = (Math.max(izq, der)) + 1;
    }

}
