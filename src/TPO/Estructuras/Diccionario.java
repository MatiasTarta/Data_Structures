package TPO.Estructuras;

public class Diccionario {
    NodoAVL raiz;

    public Diccionario(){
        raiz= null;
    }

    public boolean pertenece(NodoAVL nodo,Comparable elemento){
        boolean pertenece=false;
        int comparado= elemento.compareTo(nodo.getElemento());
    }

    public boolean insertar(Comparable clave, Object dato){
        boolean exito=true;

        return exito;
    }


}
