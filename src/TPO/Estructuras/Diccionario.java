package TPO.Estructuras;

import lineales.dinamicas.Lista;

public class Diccionario {
    NodoAVL raiz;

    public Diccionario() {
        raiz = null;
    }

    public NodoAVL getRaiz() {
        return raiz;
    }

    public boolean pertenece(NodoAVL nodo, Comparable clave) {
        if (nodo == null) {
            // caso base, si el nodo es nulo entonces se llego a una hoja sin encontrar el
            // resultado
            return false;// se corta el programa
        }
        boolean pertenece = false;
        int comparado = clave.compareTo(nodo.getClave());
        if (comparado == 0) {
            // si devuelve 0 entonces el elemento es igual
            pertenece = true;
        }
        if (comparado > 0) {
            pertenece = pertenece(nodo.getDerecho(), clave);// si es >0 se busca en el subarbol derecho
        }
        if (comparado < 0) {
            pertenece = pertenece(nodo.getIzquierdo(), clave);
        }
        return pertenece;
    }

    public boolean insertar(Comparable clave, Object dato) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoAVL(clave, dato, null, null);
            // si el arbol esta vacio pongo el elemento como raiz
        } else {
            exito = insertarAux(this.raiz, null, clave, dato);
            this.raiz.recalcularAltura();
        }
        return exito;
    }

    private boolean insertarAux(NodoAVL nodo, NodoAVL padre, Comparable clave, Object dato) {
        // Precondicion: nodo no es nulo
        boolean exito = true;
        if (clave.compareTo(nodo.getClave()) == 0) {
            // Elemento repetido
            exito = false;
        } else if (clave.compareTo(nodo.getClave()) < 0) {
            // Si el HI existe, busco la posicion de insercion en el subarbol izquierdo, si
            // no seteo el elemento
            if (nodo.getIzquierdo() != null) {
                exito = insertarAux(nodo.getIzquierdo(), nodo, clave, dato);
            } else {
                nodo.setIzquierdo(new NodoAVL(clave, dato, null, null));
            }
            nodo.recalcularAltura();
        } else if (clave.compareTo(nodo.getClave()) > 0) {
            // mismo caso de arriba
            if (nodo.getDerecho() != null) {
                exito = insertarAux(nodo.getDerecho(), nodo, clave, dato);

            } else {
                nodo.setDerecho(new NodoAVL(clave, dato, null, null));
            }
        }
        if (exito) {
            nodo.recalcularAltura();
            balancear(nodo, padre);
            nodo.recalcularAltura();
        }
        return exito;
    }

    private void balancear(NodoAVL nodo, NodoAVL padre) {
        int balanceNodo;
        int balanceHijo;
        balanceNodo = balance(nodo);
        if (nodo != null) {
            if (balanceNodo == 2) {
                // Torcido hacia la izquierda
                balanceHijo = balance(nodo.getIzquierdo());
                if (balanceHijo == 1 || balanceHijo == 0) {
                    // Rotacion simple derecha
                    if (padre == null) { // El nodo a balancear es la raiz
                        this.raiz = rotacionSimpleDerecha(nodo);
                    } else {
                        if (padre.getIzquierdo().getClave().equals(nodo.getClave())) {
                            padre.setIzquierdo(rotacionSimpleDerecha(nodo));
                        } else {
                            padre.setDerecho(rotacionSimpleDerecha(nodo));
                        }
                    }
                } else if (balanceHijo == -1) {
                    // Rotacion doble izquierda-derecha
                    nodo.setIzquierdo(rotacionSimpleIzquierda(nodo.getIzquierdo()));
                    if (padre == null) {
                        this.raiz = rotacionSimpleDerecha(nodo);
                    } else {
                        if (padre.getIzquierdo().getClave().equals(nodo.getClave())) {
                            padre.setIzquierdo(rotacionSimpleDerecha(nodo));
                        } else {
                            padre.setDerecho(rotacionSimpleDerecha(nodo));
                        }
                    }
                }
            } else if (balanceNodo == -2) {
                // Torcido hacia la derecha
                balanceHijo = balance(nodo.getDerecho());
                if (balanceHijo == -1 || balanceHijo == 0) {
                    // Rotacion simple izquierda
                    if (padre == null) { // El nodo a balancear es la raiz
                        this.raiz = rotacionSimpleIzquierda(nodo);
                    } else {
                        if (padre.getIzquierdo().getClave().equals(nodo.getClave())) {
                            padre.setIzquierdo(rotacionSimpleIzquierda(nodo));
                        } else {
                            padre.setDerecho(rotacionSimpleIzquierda(nodo));
                        }
                    }
                } else if (balanceHijo == 1) {
                    // Rotacion doble derecha-izquierda
                    nodo.setDerecho(rotacionSimpleIzquierda(nodo.getDerecho()));
                    if (padre == null) {
                        this.raiz = rotacionSimpleIzquierda(nodo);
                    } else {
                        if (padre.getIzquierdo().getClave().equals(nodo.getClave())) {
                            padre.setIzquierdo(rotacionSimpleIzquierda(nodo));
                        } else {
                            padre.setDerecho(rotacionSimpleIzquierda(nodo));
                        }
                    }
                }
            }
            nodo.recalcularAltura();
        }

    }

    private int balance(NodoAVL nodo) {
        // retorna el balance
        int balanceNodo;
        int alturaHijoIzquierdo = -1;
        int alturaHijoDerecho = -1;
        if (nodo.getIzquierdo() != null) {
            alturaHijoIzquierdo = nodo.getIzquierdo().getAltura();
        }
        if (nodo.getDerecho() != null) {
            alturaHijoDerecho = nodo.getDerecho().getAltura();
        }
        balanceNodo = alturaHijoIzquierdo - alturaHijoDerecho;
        return balanceNodo;
    }

    public NodoAVL rotacionSimpleIzquierda(NodoAVL pivote) {
        NodoAVL hijo = pivote.getDerecho();
        NodoAVL temporal = hijo.getIzquierdo();
        hijo.setIzquierdo(pivote);
        pivote.setDerecho(temporal);
        hijo.recalcularAltura();
        pivote.recalcularAltura();
        return hijo;
    }

    public NodoAVL rotacionSimpleDerecha(NodoAVL pivote) {
        NodoAVL hijo = pivote.getIzquierdo();
        NodoAVL temporal = hijo.getDerecho();
        hijo.setDerecho(pivote);
        pivote.setIzquierdo(temporal);
        hijo.recalcularAltura();
        pivote.recalcularAltura();
        return hijo;
    }

    public boolean eliminar(Comparable elem) {
        boolean exito = false;
        if (this.raiz != null) {
            exito = eliminarAux(this.raiz, null, elem);
        }
        return exito;
    }

    private boolean eliminarAux(NodoAVL n, NodoAVL padre, Comparable elem) {
        boolean exito = false;
        if (n != null) {
            if ((elem.compareTo(n.getClave())) == 0) {
                // estoy en el elemento que quiero borrar
                if (n.getIzquierdo() == null && n.getDerecho() == null) {
                    // si n no tiene hijos
                    noTieneHijos(padre, elem);
                    exito = true;

                } else if ((n.getIzquierdo() != null ^ n.getDerecho() != null)) {
                    // si n tiene UN hijo
                    tieneUnHijo(n, padre, elem); // CASO3
                    exito = true;

                } else {
                    // si n tiene ambos hijos
                    if (n.getIzquierdo().getDerecho() != null) {
                        // caso candidato
                        tieneAmbosCandidato(n.getIzquierdo(), null, n);
                    } else {
                        // caso HI
                        tieneAmbosHI(n);
                    }
                    exito = true;
                }

            } else {
                if (elem.compareTo(n.getClave()) < 0) {
                    exito = eliminarAux(n.getIzquierdo(), n, elem);
                }
                if (elem.compareTo(n.getClave()) > 0) {
                    exito = eliminarAux(n.getDerecho(), n, elem);
                }
            }

            if (exito) {
                n.recalcularAltura();
                balancear(n, padre);
                n.recalcularAltura();
            }
        }

        return exito;

    }

    // CASOS DE ELIMINAR

    private void noTieneHijos(NodoAVL padre, Comparable elem) {

        // si no tiene hijos
        if (padre == null) {
            // caso especial si el padre es nulo (raíz)
            this.raiz = null;
        } else {
            if (padre.getIzquierdo() != null) {
                if (padre.getIzquierdo().getClave().compareTo(elem) == 0) {
                    // si n es el HI de padre
                    padre.setIzquierdo(null);
                } else {
                    // si n es HD de padre
                    padre.setDerecho(null);
                }
            } else {
                // si HI es nulo entonces es el HD
                padre.setDerecho(null);
            }
        }

    }

    private void tieneUnHijo(NodoAVL n, NodoAVL padre, Comparable elem) {

        if (n.getIzquierdo() != null) {
            // si n tiene HI
            if (padre == null) {
                // caso especial si el padre es nulo
                this.raiz = n.getIzquierdo();
            } else {
                if (padre.getIzquierdo().getClave().compareTo(elem) == 0) {
                    // si n es el HI de padre
                    padre.setIzquierdo(n.getIzquierdo());
                } else {
                    // si n es el HD de padre
                    padre.setDerecho(n.getIzquierdo());
                }
            }
        }
        if (n.getDerecho() != null) {
            // si n tiene HD
            if (padre == null) {
                // caso especial si el padre es nulo
                this.raiz.setDerecho(n.getDerecho());
            } else {
                if (padre.getIzquierdo().getClave().compareTo(elem) == 0) {
                    // si n es el HI de padre
                    padre.setIzquierdo(n.getDerecho());
                } else {
                    // si n es el HD de padre
                    padre.setDerecho(n.getDerecho());
                }
            }
        }

    }

    private void tieneAmbosCandidato(NodoAVL n, NodoAVL anterior, NodoAVL raiz) {

        if (n.getDerecho() != null) {

            tieneAmbosCandidato(n.getDerecho(), n, raiz);

        } else {
            System.out.println("encontro candidato: " + n.getClave().toString());
            // encontro el candidato, setea los elementos del nodo a modificar(raíz)
            raiz.setClave(n.getClave());
            raiz.setDato(n.getDato());
            anterior.setDerecho(n.getIzquierdo());
        }

        n.recalcularAltura();
        balancear(n, anterior);
        n.recalcularAltura();

    }

    private void tieneAmbosHI(NodoAVL n) {
        n.setClave(n.getIzquierdo().getClave());
        n.setDato(n.getIzquierdo().getDato());
        n.setIzquierdo(n.getIzquierdo().getIzquierdo());
    }

    public Object obtenerDato(Comparable clave) {
        // busca en el arbol el elemento con la clave y retorna el dato
        Object resultado = null;
        if (this.raiz != null) {
            resultado = buscarDato(this.raiz, clave);
            if (resultado == null) {
                resultado = "La clave no existe";
            }
        }
        return resultado;
    }

    private Object buscarDato(NodoAVL nodo, Comparable clave) {
        Object retorno = null;
        if (nodo != null) {
            // Si la clave buscada coincide con la clave del nodo
            if (clave.compareTo(nodo.getClave()) == 0) {
                retorno = nodo.getDato();// caso base
            }
            // Si la clave buscada es menor, sigue buscando en el hijo izquierdo
            else if (clave.compareTo(nodo.getClave()) < 0) {
                retorno = buscarDato(nodo.getIzquierdo(), clave);
            }
            // Si la clave buscada es mayor, sigue buscando en el hijo derecho
            else if (clave.compareTo(nodo.getClave()) > 0) {
                retorno = buscarDato(nodo.getDerecho(), clave);
            }
        }
        return retorno;
    }

    public boolean existeClave(Comparable clave) {
        //verifica si el elemento esta en la estructura
        boolean resultado = false;
        if (this.raiz != null) {
            resultado = existeClaveAux(this.raiz, clave);
        }
        return resultado;
    }

    private boolean existeClaveAux(NodoAVL nodo, Comparable clave) {
        boolean exito = false;
        if (nodo != null) {
            // Si la clave buscada coincide con la clave del nodo
            if (clave.compareTo(nodo.getClave()) == 0) {
                exito = true;
            }
            // Si la clave buscada es menor, sigue buscando en el hijo izquierdo
            else if (clave.compareTo(nodo.getClave()) < 0) {
                exito = existeClaveAux(nodo.getIzquierdo(), clave);
            }
            // Si la clave buscada es mayor, sigue buscando en el hijo derecho
            else if (clave.compareTo(nodo.getClave()) > 0) {
                exito = existeClaveAux(nodo.getDerecho(), clave);
            }
        }
        return exito;
    }

    public Comparable maximoElemento() {
        /* Devuelve la clave mayor del árbol. Si el árbol está vacío, retorna null */
        if (this.raiz == null) {
            return null; // Árbol vacío
        }
        return buscarMaximo(this.raiz);
    }

    private Comparable buscarMaximo(NodoAVL nodo) {
        // Sigue moviéndose hacia la derecha hasta encontrar el nodo más a la derecha
        if (nodo.getDerecho() == null) {
            return nodo.getClave(); // El nodo actual es el mayor
        }
        return buscarMaximo(nodo.getDerecho());
    }

    public Comparable minimoElemento() {
        /* Devuelve la clave menor del árbol. Si el árbol está vacío, retorna null */
        if (this.raiz == null) {
            return null; // Árbol vacío
        }
        return buscarMinimo(this.raiz);
    }

    private Comparable buscarMinimo(NodoAVL nodo) {
        // Sigue moviéndose hacia la izquierda hasta encontrar el nodo más a la
        // izquierda
        if (nodo.getIzquierdo() == null) {
            return nodo.getClave(); // El nodo actual es el menor
        }
        return buscarMinimo(nodo.getIzquierdo());
    }

    public Lista ciudadesPrefijo(int prefijo) {
        Lista listaCiudades = new Lista();
        if (this.raiz != null) {
            buscarCiudadesPorPrefijo(this.raiz, prefijo, listaCiudades);
        }
        return listaCiudades;
    }

    private void buscarCiudadesPorPrefijo(NodoAVL nodo, int prefijo, Lista listaCiudades) {
        if (nodo != null) {
            String claveActual = nodo.getClave().toString();
            String prefijoStr = String.valueOf(prefijo);
            // Verifica si la clave contiene el prefijo
            if (claveActual.contains(prefijoStr)) {
                listaCiudades.insertar(nodo.getDato(), listaCiudades.longitud() + 1);
            }
            buscarCiudadesPorPrefijo(nodo.getIzquierdo(), prefijo, listaCiudades);
            buscarCiudadesPorPrefijo(nodo.getDerecho(), prefijo, listaCiudades);
        }
    }

    public String toString() {
        if (raiz == null) {
            return "Árbol vacío";
        }
        StringBuilder sb = new StringBuilder();
        toStringTree(raiz, sb, 0, true, "", "");
        return sb.toString();
    }

    private void toStringTree(NodoAVL nodo, StringBuilder sb, int depth, boolean isLeft, String prefix, String branch) {
        if (nodo != null) {
            sb.append(prefix);
            sb.append(isLeft ? "└── " : "├── ");
            sb.append(branch).append(nodo.getClave()).append("\n");

            String childPrefix = prefix + (isLeft ? "    " : "│   ");
            toStringTree(nodo.getIzquierdo(), sb, depth + 1, false, childPrefix, "I-");
            toStringTree(nodo.getDerecho(), sb, depth + 1, true, childPrefix, "D-");
        }
    }

    public void vaciar() {
        this.raiz = null;
    }

    public Lista listarClaves(){
        Lista claves= new Lista();
        listarClavesAux(raiz,claves);
        return claves;
    }
    public static void listarClavesAux(NodoAVL nodo,Lista claves){
        if (nodo!=null) {
            listarClavesAux(nodo.getIzquierdo(), claves);
            claves.insertar(nodo.getClave(), claves.longitud()+1);
            listarClavesAux(nodo.getDerecho(), claves);
        }
    }

    public Lista listarDatos() {
        Lista datos = new Lista();
        listarDatosAux(raiz, datos);
        return datos;
    }
    
    private void listarDatosAux(NodoAVL nodo, Lista datos) {
        if (nodo != null) {
            listarDatosAux(nodo.getIzquierdo(), datos);
            datos.insertar(nodo.getDato(), datos.longitud() + 1);
            listarDatosAux(nodo.getDerecho(), datos);
        }
    }
    

}
