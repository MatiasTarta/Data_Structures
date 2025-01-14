package TPO.Estructuras;


public class NodoAVL {

    private Comparable clave;//codigo postal de la ciudad
    private Object dato;
    private int altura;
    private NodoAVL izquierdo;
    private NodoAVL derecho;

    public NodoAVL(Comparable clave, Object dato, NodoAVL izquierdo, NodoAVL derecho) {
        this.clave = clave;
        this.dato = dato;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.altura=0;
        recalcularAltura();//por si ingresan por parametro los hijos debe recalcular las alturas
    }

    public Comparable getClave() {
        return clave;
    }

    public void setClave(Comparable clave) {
        this.clave = clave;
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

    public void recalcularAltura(){
        int alturaIzq,alturaDer;
        if(izquierdo==null){
            alturaIzq=-1;
        } else {
            alturaIzq = izquierdo.getAltura();
        }
        if(derecho==null){
            alturaDer = -1;
        } else {
            alturaDer = derecho.getAltura();
        }
            altura = Math.max(alturaIzq, alturaDer)+1;
    }

    public NodoAVL getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoAVL izquierdo) {
        this.izquierdo = izquierdo;
        recalcularAltura();
    }

    public NodoAVL getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoAVL derecho) {
        this.derecho = derecho;
        recalcularAltura();
    }
}