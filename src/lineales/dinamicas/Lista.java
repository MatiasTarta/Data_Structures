package lineales.dinamicas;

public class Lista {
    private Nodo cabecera;
    private int longitud;

    public Lista() {
        this.cabecera = null;
        this.longitud = 0;
    }

    public boolean insertar(Object nuevoElem, int pos) {
        boolean exito = false;
        if (pos > 0 && pos <= this.longitud + 1) {
            if (pos == 1) {
                cabecera = new Nodo(nuevoElem, this.cabecera);
                exito = true;
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(new Nodo(nuevoElem, aux.getEnlace()));
                exito = true;
            }
            longitud++;
        }
        return exito;
    }

    public boolean eliminar(int pos) {
        boolean exito = false;
        if (pos > 0 && pos <= this.longitud) {
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
            exito = true;
            longitud--;
        }
        return exito;
    }

    public int longitud() {
        return this.longitud;
    }

    public boolean esVacia() {
        return longitud == 0;
    }

    public void vaciar() {
        this.cabecera = null;
        this.longitud = 0;
    }

    public Object recuperar(int pos) {
        Object recuperado = null;
        if (pos >= 1 && pos <= this.longitud) {
            if (pos == 1) {
                recuperado = this.cabecera.getElem();
            } else {
                Nodo aux = this.cabecera;
                int i = 0;
                while (i < pos - 2) {
                    aux = aux.getEnlace();
                    i++;
                }
                recuperado = aux.getEnlace().getElem();
            }
        }
        return recuperado;
    }

    public int localizar(Object buscado) {
        int pos = -1;
        if (!this.esVacia()) {
            int i = 1;
            Nodo aux = this.cabecera;
            while (aux != null && !aux.getElem().equals(buscado)) {
                // mientras no llegue al final y no encuentre el objeto avanzo
                aux = aux.getEnlace();
                i++;
            }
            if (aux != null) {
                // si se corto porque encontro el objeto retorna el nodo donde se encuentra el
                // buscado
                pos = i;
            }
        }
        return pos;
    }

    public String toString() {
        String s = "[";
        Nodo aux = this.cabecera;
        while (aux != null) {
            s = s + aux.getElem() + "|";
            aux = aux.getEnlace();
        }
        return s + "]";
    }

    public Lista invertir(Lista l1) {
        Lista listaInvertida = new Lista();
        int i, j = 1;
        for (i = l1.longitud(); i > 0; i--) {
            listaInvertida.insertar(l1.recuperar(i), j);
            j++;
        }
        return listaInvertida;
    }

    public Lista clone() {
        Lista listaClon = new Lista();
        if (!esVacia()) {
            listaClon.cabecera = new Nodo(this.cabecera.getElem(), null);
            // asigno a lista clon mi cabecera sin enlace
            cloneAux(listaClon.cabecera, this.cabecera.getEnlace());
            // clona los nodos recursivamente
            listaClon.longitud = this.longitud;
        }
        return listaClon;
    }

    private void cloneAux(Nodo nodoTemp, Nodo nodoEnlace) {
        // modulo recursivo para clonar una estructuira dinamica de Nodos
        // nodoTemp es usado para construir el clon, nodoEnlace es el nodo actual de la
        // cola
        if (nodoEnlace != null) {
            // condicion de salida que se llegue al final de la estructura
            nodoTemp.setEnlace(new Nodo(nodoEnlace.getElem(), null));
            // crea el siguiente nodo de nodoTemp con el valor del nodoEnlace.
            cloneAux(nodoTemp.getEnlace(), nodoEnlace.getEnlace());
            // llama al modulo con el nodo recien Creado y el siguiente nodo de la
            // estructura
        }
    }

    public Lista concatenar(Lista l1, Lista l2) {
        Lista listaConcatenada = new Lista();
        listaConcatenada = l1.clone();
        for (int i = 1; i <= l2.longitud(); i++) {
            Object elem = l2.recuperar(i);
            listaConcatenada.insertar(elem, l1.longitud() + i);
        }
        return listaConcatenada;

    }

    public Nodo obtenerUltimoNodo(Lista l1) {
        if (l1.cabecera == null) {
            return null; // la lista está vacía
        } else {
            Nodo nodoAux = l1.cabecera;
            while (nodoAux.getEnlace() != null) {
                nodoAux = nodoAux.getEnlace();
            }
            return nodoAux; // devuelve el último nodo
        }
    }

    public Lista obtenerMultiplos(int x) {
        Lista lis = new Lista();
        if (this.cabecera != null) {
            lis.cabecera = obtenerMultiplosAux(this.cabecera, x, 1);
        }
        return lis;
    }

    private Nodo obtenerMultiplosAux(Nodo nodo, int x, int pos) {
        Nodo nuevo = null;
        if (nodo != null) {
            if (pos % x == 0) {
                // si es multiplo creo un nuevo nodo con el elemento
                nuevo = new Nodo(nodo.getElem(), null);
                nuevo.setEnlace(obtenerMultiplosAux(nodo.getEnlace(), x, pos + 1));
                // enlazo el nuevo nodo con el siguiente nodo que cumpla, avanzo 1 la posicion
            } else {
                nuevo = obtenerMultiplosAux(nodo.getEnlace(), x, pos + 1);
                // si no se cumplia la posicion avanzo en la lista
            }
        }
        return nuevo;
    }

    public void eliminarApariciones(Object x) {
        Nodo aux = this.cabecera;
        Nodo enlace = aux;
        // usamos un nodo para recorrer la listaq y otro para setear los enclaces
        int i = 1;
        while (i <= this.longitud && aux.getEnlace() != null) {
            if ((aux.getEnlace()).getElem().equals(x)) {
                enlace.setEnlace(aux.getEnlace().getEnlace());
            }
            aux = aux.getEnlace();
            i++;
        }
    }

    public void cambiarPosicion(int pos1, int pos2) {
        if (!(pos1 == pos2) && pos1 > 0 && pos2 > 0) {
            Nodo aux = this.cabecera;
            if (pos1 == 1 && pos2 != 1) {
                cambiarCabeceraAux(pos1, pos2, aux);
            } else if (pos2 == 1 && pos1 != 1) {
                cambiarCabeceraAux(pos2, pos1, aux);
            } else if (pos2 > pos1 && pos2 <= this.longitud && pos1 != 1 && pos2 != 1) {
                cambiarPosicionAux(pos1, pos2, aux);
            } else if (pos1 > pos2 && pos1 <= this.longitud && pos1 != 1 && pos2 != 1) {
                cambiarPosicionAux(pos2, pos1, aux);
            }

        }

    }

    private void cambiarPosicionAux(int pos1, int pos2, Nodo aux) {
        for (int i = 1; i < pos1 - 1; i++) {
            aux = aux.getEnlace();
        }
        Object x = aux.getEnlace().getElem();
        Nodo aux2 = aux.getEnlace();
        for (int j = pos1; j < pos2 - 1; j++) {
            aux2 = aux2.getEnlace();
        }
        Object y = aux2.getEnlace().getElem();
        aux2.setEnlace(new Nodo(x, aux2.getEnlace().getEnlace()));
        aux.setEnlace(new Nodo(y, aux.getEnlace().getEnlace()));
    }

    private void cambiarCabeceraAux(int pos1, int pos2, Nodo aux) {
        for (int i = 1; i < pos2 - 1; i++) {
            aux = aux.getEnlace();
        }
        Object x = aux.getEnlace().getElem();
        aux.setEnlace(new Nodo(this.cabecera.getElem(), aux.getEnlace().getEnlace()));
        this.cabecera = new Nodo(x, this.cabecera.getEnlace());
    }
}
