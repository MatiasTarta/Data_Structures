package TPO.Estructuras;
public class Diccionario {
    private NodoAVLDicc raiz;
    public Diccionario() {
        raiz = null;
    }

    public NodoAVLDicc getRaiz() {
        return raiz;
    }

    public boolean pertenece(NodoAVLDicc nodo, Comparable clave) {
        boolean pertenece = false;
        if (nodo == null) {
            // caso base, si el nodo es nulo entonces se llego a una hoja sin encontrar el
            // resultado se corta el programa
        }else{
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
        }
        return pertenece;
    }

    public boolean insertar(Comparable clave, Object dato) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoAVLDicc(clave, dato, null, null);
            // si el arbol esta vacio pongo el elemento como raiz
        } else {
            exito = insertarAux(this.raiz, null, clave, dato);
            this.raiz.recalcularAltura();
        }
        return exito;
    }

    private boolean insertarAux(NodoAVLDicc nodo, NodoAVLDicc padre, Comparable clave, Object dato) {
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
                nodo.setIzquierdo(new NodoAVLDicc(clave, dato, null, null));
            }
        } else  {
            //el elemento a insertar es mayor
            if (nodo.getDerecho() != null) {
                //si ya existe busco recursivamente en el subarbol derecho
                exito = insertarAux(nodo.getDerecho(), nodo, clave, dato);
            } else {
                //estoy en la posicion de insercion
                nodo.setDerecho(new NodoAVLDicc(clave, dato, null, null));
            }
        }
        if (exito) {
            balancear(nodo, padre);
        }
        return exito;
    }

    private void balancear(NodoAVLDicc nodo, NodoAVLDicc padre) {
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
            nodo.recalcularAltura();//sin esta instruccion el arbol entero se desbalancea a la derecha
        }
    }

    private int balance(NodoAVLDicc nodo) {
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

    public NodoAVLDicc rotacionSimpleIzquierda(NodoAVLDicc pivote) {
        NodoAVLDicc hijo = pivote.getDerecho();
        NodoAVLDicc temporal = hijo.getIzquierdo();
        hijo.setIzquierdo(pivote);
        pivote.setDerecho(temporal);
        hijo.recalcularAltura();
        pivote.recalcularAltura();
        return hijo;
    }

    public NodoAVLDicc rotacionSimpleDerecha(NodoAVLDicc pivote) {
        NodoAVLDicc hijo = pivote.getIzquierdo();
        NodoAVLDicc temporal = hijo.getDerecho();
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

    private boolean eliminarAux(NodoAVLDicc n, NodoAVLDicc padre, Comparable elem) {
        boolean exito = false;
        if (n != null) {
            if ((elem.compareTo(n.getClave())) == 0) {
                // estoy en el elemento que quiero borrar
                if (n.getIzquierdo() == null && n.getDerecho() == null) {
                    // si n no tiene hijos
                    noTieneHijos(padre, elem);
                    exito = true;
                } else if ((n.getIzquierdo() != null ^ n.getDerecho() != null)) {//XOR (^)
                    // si n tiene UN hijo
                    tieneUnHijo(n, padre); // CASO3
                    exito = true;

                } else {
                    // si n tiene ambos hijos
                    if (n.getIzquierdo().getDerecho() != null) {
                        // caso candidato
                        tieneAmbosCandidato(n.getIzquierdo(), null, n);
                    }else {
                        // caso HI
                        //caso para cuando solo tiene 2 hijos que son hojas. Reemplaza el nodo con el hijo I
                        reemplazarConHijoIzquierdo(n);
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
                balancear(n, padre);
            }
        }
        return exito;
    }

    // CASOS DE ELIMINAR

    private void noTieneHijos(NodoAVLDicc padre, Comparable elem) {
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

    private void tieneUnHijo(NodoAVLDicc n, NodoAVLDicc padre) {
        if(padre!=null){
            if(n.getDerecho()==null){
                    if(((Comparable) n.getClave()).compareTo( padre.getClave()) < 0 ){
                        padre.setIzquierdo(n.getIzquierdo());
                    }else{//si el hijo izquierdo es mayor que su padre
                        padre.setDerecho(n.getIzquierdo());
                    }
            }else{//su hijo izquierdo es null(solo tiene hijo derecho)
                    if(((Comparable) n.getClave() ).compareTo(padre.getClave())  < 0 ){//y el hijo izquierdo es menor que su padre
                        padre.setIzquierdo(n.getDerecho());
                    }else{//si el hijo izquierdo es mayor que su padre
                        padre.setDerecho(n.getDerecho());
                    }
            }
     }else{//caso espacial:si el elemento es raiz lo intercambio por su hijo
          if(n.getIzquierdo()==null){
               this.raiz=n.getDerecho();
          }else{
               this.raiz=n.getIzquierdo();
          }
     }
    }

    private void tieneAmbosCandidato(NodoAVLDicc n, NodoAVLDicc anterior, NodoAVLDicc raiz) {
        if (n.getDerecho() != null) {
            tieneAmbosCandidato(n.getDerecho(), n, raiz);
        } else {
            System.out.println("encontro candidato: " + n.getClave().toString());
            // encontro el candidato, setea los elementos del nodo a modificar(raíz)
            raiz.setClave(n.getClave());
            raiz.setDato(n.getDato());
            anterior.setDerecho(n.getIzquierdo());
        }
        balancear(n, anterior);
    }

    private void reemplazarConHijoIzquierdo(NodoAVLDicc n) {
        
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

    private Object buscarDato(NodoAVLDicc nodo, Comparable clave) {
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

    private boolean existeClaveAux(NodoAVLDicc nodo, Comparable clave) {
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

    private Comparable buscarMaximo(NodoAVLDicc nodo) {
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

    private Comparable buscarMinimo(NodoAVLDicc nodo) {
        // Sigue moviéndose hacia la izquierda hasta encontrar el nodo más a la
        // izquierda
        if (nodo.getIzquierdo() == null) {
            return nodo.getClave(); // El nodo actual es el menor
        }
        return buscarMinimo(nodo.getIzquierdo());
    }

    

    public String toString() {
        if (raiz == null) {
            return "Árbol vacío";
        }
        StringBuilder sb = new StringBuilder();
        toStringTree(raiz, sb, 0, true, "", "");
        return sb.toString();
    }

    private void toStringTree(NodoAVLDicc nodo, StringBuilder sb, int depth, boolean isLeft, String prefix, String branch) {
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
    
    private static void listarClavesAux(NodoAVLDicc nodo,Lista claves){
        if (nodo!=null) {
            listarClavesAux(nodo, claves);
        }
    }

    public Lista listarDatos() {
        Lista datos = new Lista();
        listarDatosAux(raiz, datos);
        return datos;
    }
    
    private void listarDatosAux(NodoAVLDicc nodo, Lista datos) {
        if (nodo != null) {
            listarDatosAux(nodo.getIzquierdo(), datos);
            datos.insertar(nodo.getDato(), datos.longitud() + 1);
            listarDatosAux(nodo.getDerecho(), datos);
        }
    }


    public Lista ciudadesPrefijo(int prefijo) {
        Lista listaCiudades = new Lista();
        if (this.raiz != null) {
            buscarCiudadesPorPrefijo(this.raiz, prefijo, listaCiudades);
        }
        return listaCiudades;
    }

    private void buscarCiudadesPorPrefijo(NodoAVLDicc nodo, int prefijo, Lista listaCiudades) {
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
   

}
