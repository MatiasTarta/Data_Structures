package conjuntistas;
import lineales.dinamicas.*;

public class ArbolABB {
    private NodoArbol raiz;

    public ArbolABB() {
        this.raiz = null;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public boolean insertar(Comparable elemNuevo) {
        boolean exito = false;
        if (esVacio()) {
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            exito = insertarAux(elemNuevo, this.raiz);
        }
        return exito;
    }

    public boolean insertarAux(Comparable elemNuevo, NodoArbol n) {
        int compareTo = elemNuevo.compareTo(n.getElem());
        boolean exito = false;

        if (compareTo != 0) {
            if (compareTo < 0) {
                // elemento menor que n.getElem()
                // si tiene HI baja a la izquierda, sino agrega elemento
                if (n.getIzquierdo() != null) {
                    exito = insertarAux(elemNuevo, n.getIzquierdo());
                } else {
                    n.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                }
            } else {
                // elemento es mayor que n.getElem()
                // si tiene HD baja a la derecha, sino agrega elemento
                if (n.getDerecho() != null) {
                    exito = insertarAux(elemNuevo, n.getDerecho());
                } else {
                    n.setDerecho(new NodoArbol(elemNuevo, null, null));
                }
            }
        } else {
            exito = false;
            // error de elemento repetido
        }
        return exito;
    }

    public boolean eliminar(Comparable elem){
        boolean exito = false;
        if (!esVacio()) {
            exito = eliminarAux(elem, this.raiz);
        }
        return exito;
    }

    private boolean eliminarAux(Comparable elem, NodoArbol n){
        boolean exito=false;
        if(n!=null){
            if(elem.compareTo(n)==0){

            }
        }


        return exito;
    }

    public Lista listar() {
        Lista lista = new Lista();
        if (this.raiz != null) {
            listarAux(this.raiz, lista);
        }
        return lista;
    }

    private void listarAux(NodoArbol node, Lista lista) {
        if (node != null) {
            listarAux(node.getDerecho(), lista);
            lista.insertar(node.getElem(), 1);
            listarAux(node.getIzquierdo(), lista);
        }
    }

}
