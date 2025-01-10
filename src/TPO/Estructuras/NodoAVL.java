package TPO.Estructuras;

public class NodoAVL {
    Object elemento;
    int altura;
    NodoAVL izquierdo,derecho;

    public NodoAVL(Object elemento, int a, NodoAVL izquierdo, NodoAVL derecho){
        this.elemento=elemento;
        this.altura=a;
        this.izquierdo=izquierdo;
        this.derecho=derecho;
    }
}
