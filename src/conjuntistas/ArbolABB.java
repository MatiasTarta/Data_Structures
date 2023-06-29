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

    /*
     * public boolean eliminar(Comparable elem){
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
     */

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

    public boolean eliminarelemanterior(Comparable elem){
        boolean exito= false;
        NodoArbol n= obtenerNodo(this.raiz,elem);
        exito= eliminarelemanteriorAux(n);
        return exito;
    }
    private boolean eliminarelemanteriorAux(NodoArbol n){
        //Metodo privado para evauar ciertos casos trabajando con nodos
        boolean exito= false;
        if(n.getIzquierdo()!=null){
            if(esHojaDerecha(n.getIzquierdo())){
                n.setIzquierdo(n.getIzquierdo().getIzquierdo());
                exito=true; //si el mayor elemento del subArbol es hijo inmdiat de n
            }else{
                exito=eliminarAux(n.getIzquierdo());
            }
        }
        return exito;
    }
    private boolean esHojaDerecha(NodoArbol n){
        return (n.getDerecho()==null);
    }
    private NodoArbol obtenerNodo(NodoArbol n,Comparable elem){
        NodoArbol aux= null;
        if(n!=null){
            if(n.getElem().compareTo(elem)==0){
                aux=n;
            }else{
                aux=obtenerNodo(n.getIzquierdo(),elem);//busco recursivamente por I
                if(aux==null){
                    aux=obtenerNodo(n.getDerecho(),elem);//busco recursivamente por D
                }
            }
        }
        return aux;
    }
    private boolean eliminarAux(NodoArbol n){
        boolean exito=false;
        if(n!=null){
            NodoArbol hijoD= n.getDerecho();
            if(hijoD.getDerecho()!=null){
                exito= eliminarAux(hijoD);
            }else{
                n.setDerecho(null);
                exito=true;
            }
        }
        return exito;
    }





    public String toString() {
        String cadena;
        cadena = imprimir(this.raiz);
        return cadena;
    }

    private String imprimir(NodoArbol nodo) {
        String cadena = "";
        if (nodo != null) {
            if (nodo.getDerecho() != null || nodo.getIzquierdo() != null) {
                cadena += "Nodo: " + nodo.getElem();
                if (nodo.getIzquierdo() != null) {
                    cadena += "[ HI: " + nodo.getIzquierdo().getElem() + " ]";
                } else {
                    cadena += "[ HI: null ]";
                }
                if (nodo.getDerecho() != null) {
                    cadena += "[ HD: " + nodo.getDerecho().getElem() + " ]" + "\n";
                } else {
                    cadena += "[ HD: null ] \n";
                }
                cadena += imprimir(nodo.getIzquierdo());
                cadena += imprimir(nodo.getDerecho());
            } else {
                cadena += "[ Hoja: " + nodo.getElem() + "]" + "\n";
            }
        }
        return cadena;
    }

    /**
     * Reutilizo el clone de Arbol Binario Orden O(n)
     */


}
