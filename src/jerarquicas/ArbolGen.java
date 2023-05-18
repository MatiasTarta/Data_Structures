package jerarquicas;

import lineales.dinamicas.*;

public class ArbolGen {
    private NodoGen raiz;

    public ArbolGen() {
        raiz = null;
    }

    public boolean insertar(Object hijo, Object elemPadre) {
        boolean exito = false;
        NodoGen nodoNuevo = new NodoGen(hijo, null, null);
        if (esVacio()) {
            this.raiz = new NodoGen(hijo, raiz, raiz);
            exito = true;
            // si el arbol esta vacio, inserta el elemento en la raiz
        } else {
            NodoGen nodo = obtenerNodo(this.raiz, elemPadre);
            // utilizo el modulo obtenerNodo para buscar localizar el elemento padre que
            // quiero
            if (nodo != null) {
                // condicion que el nodo no sea nulo
                if (nodo.getHijoIzquierdo() == null) {
                    nodo.setHijoIzquierdo(nodoNuevo);
                    // si mi nodo padre no tiene hijo izquierdo lo inserto como nuevo hijo izquierdo
                } else {
                    nodo = nodo.getHijoIzquierdo();
                    // si mi nodo Padre tiene hijos me paro en el hijo izquierdo y recorro sus
                    // hermanos
                    while (nodo.getHermanoDerecho() != null) {
                        nodo = nodo.getHermanoDerecho();
                        // recorre los hermanos derechos hasta que llega al ultimo
                    }
                    nodo.setHermanoDerecho(nodoNuevo);
                    // inserto como ultimo en los hermanos del hijo izquierdo
                }
                exito = true;
            }
        }
        return exito;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public Lista ancestros(Object elem) {
        Lista l = new Lista();
        if (!elem.equals(this.raiz.getElem())) {
            ancestrosAux(elem, this.raiz, l);
        }
        return l;
    }

    private boolean ancestrosAux(Object elem, NodoGen n, Lista l) {
        boolean flag = false;
        // flag es una variable de tipo booleana que verifica si el elemento que busco
        // se encuentra en la subrama en la que estoy parado
        if (n != null && !flag) {
            // siempre que el nodo sea distinto de nulo continuo
            if (n.getElem().equals(elem)) {
                flag = true;
                // si el elemento es encontrado mi bandera es verdadera
            } else {
                if (n.getHijoIzquierdo() != null) {
                    // si tengo hijo izquierdo lo llamo con la variable aux hijo
                    NodoGen hijo = n.getHijoIzquierdo();
                    while (hijo != null && !flag) {
                        flag = ancestrosAux(elem, hijo, l);
                        hijo = hijo.getHermanoDerecho();
                        System.out.println(flag);
                    }
                    if (flag) {
                        l.insertar(n.getElem(), l.longitud() + 1);
                    }
                }
            }
        }
        return flag;
    }

    public boolean pertenece(Object elemento) {
        return perteneceAux(elemento, this.raiz);
    }

    private boolean perteneceAux(Object elemento, NodoGen nodo) {
        boolean encontrado = false;
        // mientras el arbol no sea nulo que busque
        if (nodo != null) {
            // si lo encuentra en la raiz retorna true sino lo busca en el hijo izquierdo
            if (nodo.getElem().equals(elemento)) {
                encontrado = true;
            } else {
                // si tiene hijo izquierdo que busque en el hijo izquierdo
                if (nodo.getHijoIzquierdo() != null) {

                    NodoGen hijo = nodo.getHijoIzquierdo();
                    while (hijo != null && !encontrado) {
                        encontrado = perteneceAux(elemento, hijo);
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            }
        }
        return encontrado;
    }

    private NodoGen obtenerNodo(NodoGen n, Object elem) {
        NodoGen nodoTemp = null;
        if (n != null) {
            // si es distinto de nulo
            if (n.getElem().equals(elem)) {
                // llegue al nodo que busco, se corta el ciclo
                nodoTemp = n;
            } else {
                
                 nodoTemp = obtenerNodo(n.getHermanoDerecho(), elem);
                 // avanza por los hermanos derechos del nodo
                  if (nodoTemp == null) {
                  nodoTemp = obtenerNodo(n.getHijoIzquierdo(), elem);
                  // si no lo encontre entre todos sus hermanos,avanzo al hijo izquierdo
                  }
                
            }
        }
        return nodoTemp;
    }

    public Object padre(Object elemento) {
        Object resultado;
        NodoGen aux;
        // caso especial: la primer aparicion del elem es la raiz (no tiene padre)
        if (raiz.getElem().equals(elemento) || esVacio()) {
            resultado = null;
        } else {
            aux = padreAux(elemento, this.raiz);
            resultado = aux.getElem();
        }
        return resultado;
    }

    private NodoGen padreAux(Object elemento, NodoGen nodo) {
        NodoGen pos = null;
        // si no es vacio
        if (nodo != null) {
            // si esta justo en el hijo izquierdo, guardo el nodo padre
            if (nodo.getHijoIzquierdo().getElem().equals(elemento)) {
                pos = nodo;
            } else {
                // sino busca en los demas hijos izquierdos
                pos = padreAux(elemento, nodo.getHijoIzquierdo());
            }
            if (pos == null) {
                // si pos e
                pos = padreAux(elemento, nodo.getHermanoDerecho());
            }
        }

        return pos;

    }

    public int altura() {
        int aux = 0;
        // si es vacio, su altura es 0
        if (!esVacio()) {
            aux = alturaAux(raiz);
        }
        return aux;
    }

    private int alturaAux(NodoGen nodo) {
        int alt, altHermano = 0, altHijo = 0;
        // si el nodo es hoja, empieza la cuenta para arriba
        if (nodo == null) {
            alt = 1;
        } else {
            // si tiene hijo busco en el hijo
            if (nodo.getHijoIzquierdo() != null) {
                altHijo = alturaAux(nodo.getHijoIzquierdo()) + 1;
            }
            // sino busco en el hermano
            if (nodo.getHermanoDerecho() != null) {
                altHermano = alturaAux(nodo.getHermanoDerecho()) + 1;
            }
            // si la altura del hijo es mayor a la del hermano, me quedo con la del hijo
            if (altHijo > altHermano) {
                alt = altHijo;
            } else {
                // sino, con la altura del subarbol del hermano
                alt = altHermano;
            }
        }
        return alt;
    }

    public int nivel(Object buscado) {
        // devuelve el nivel dodne se ecneuntra el nodo que buscamos
        int niv = -1;
        // inicializa en -1
        if (this.raiz != null) {
            // si mi raiz no es nula entra
            niv = nivelAux(this.raiz, buscado, 0);
        }
        return niv;
    }

    private int nivelAux(NodoGen n, Object buscado, int profundidad) {
        int nivel = -1;
        if (n != null) {
            if (n.getElem() != buscado) {
                // condicion base 2 que el element que busco todavia no haya sido encontrado
                nivel = nivelAux(n.getHermanoDerecho(), buscado, profundidad);
                // llamo recursivamente a sus hermanos, la profundidad no varia, porque son
                // hermanos, no hijos
                if (nivel == -1) {
                    // si devuelve -1, en los hermanos no estaba,avanzo a los hijos
                    nivel = nivelAux(n.getHijoIzquierdo(), buscado, profundidad + 1);
                }
            } else {
                nivel = profundidad;
            }
        }
        return nivel;
    }

    public Lista listarPreorden() {
        Lista l = new Lista();
        listarPreordenAux(this.raiz, l);
        return l;
    }

    private void listarPreordenAux(NodoGen n, Lista ls) {
        if (n != null) {
            ls.insertar(n.getElem(), ls.longitud() + 1);
            if (n.getHijoIzquierdo() != null) {
                listarInordenAux(n.getHijoIzquierdo(), ls);
            }

            if (n.getHijoIzquierdo() != null) {
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarInordenAux(hijo, ls);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public Lista listarPosorden() {
        Lista l = new Lista();
        listarPosordenAux(this.raiz, l);
        return l;
    }

    private void listarPosordenAux(NodoGen n, Lista ls) {
        if (n != null) {
            if (n.getHijoIzquierdo() != null) {
                listarInordenAux(n.getHijoIzquierdo(), ls);
            }

            if (n.getHijoIzquierdo() != null) {
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarInordenAux(hijo, ls);
                    hijo = hijo.getHermanoDerecho();
                }
            }
            ls.insertar(n.getElem(), ls.longitud() + 1);
        }
    }

    public Lista listarInorden() {
        Lista l = new Lista();
        listarInordenAux(this.raiz, l);
        return l;
    }

    private void listarInordenAux(NodoGen n, Lista ls) {
        if (n != null) {
            // llamado recursivo con primer hijo de n
            if (n.getHijoIzquierdo() != null) {
                listarInordenAux(n.getHijoIzquierdo(), ls);
            }
            // visita del nodo n
            ls.insertar(n.getElem(), ls.longitud() + 1);
            if (n.getHijoIzquierdo() != null) {
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarInordenAux(hijo, ls);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public Lista listarPorNiveles() {
        Lista l = new Lista();
        listarPorNivelesAux(this.raiz, l);
        // llama a un metodo recursivo
        return l;
    }

    private void listarPorNivelesAux(NodoGen n, Lista l) {

    }

    public String toString() {
        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoGen n) {
        String s = "";
        if (n != null) {
            s += n.getElem().toString() + "->";
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += hijo.getElem().toString() + ",";
                hijo = hijo.getHermanoDerecho();
            }
            hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return s;
    }

}
