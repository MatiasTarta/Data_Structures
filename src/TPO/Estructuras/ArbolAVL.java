package TPO.Estructuras;

public class ArbolAVL {
    NodoAVL raiz;

    public ArbolAVL(){
        raiz=null;
    }
    public NodoAVL getRaiz() {
        return raiz;
    }

    public boolean pertenece(NodoAVL nodo,Comparable clave){
        if (nodo==null) {
            //caso base, si el nodo es nulo entonces se llego a una hoja sin encontrar el resultado
            return false;//se corta el programa
        }
        boolean pertenece=false;
        int comparado= clave.compareTo(nodo.getClave());
        if (comparado==0){
            //si devuelve 0 entonces el elemento es igual
            pertenece=true;
        }
        if (comparado>0) {
            pertenece=pertenece(nodo.getDerecho(), clave);//si es >0 se busca en el subarbol derecho
        }
        if (comparado<0) {
            pertenece= pertenece(nodo.getIzquierdo(), clave);
        }
        return pertenece;
    }

    public boolean insertar(Comparable clave, Object dato) {
        /* Recibe un elemento y lo agrega en el arbol de manera ordenada. Si el 
        elemento ya se encuentra en el árbol no se realiza la inserción. Devuelve 
        verdadero si el elemento se agrega a la estructura y falso en caso contrario */
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoAVL(clave, dato, null, null);
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
            // elemento es menor a nodo.getElem()
            // Si tiene HI baja a la izquierda, sino lo setea
            if (nodo.getIzquierdo() != null) {
                exito = insertarAux(nodo.getIzquierdo(), nodo, clave, dato);
               //NO VA balancear(nodo.getIzquierdo(), nodo);
            } else {
                nodo.setIzquierdo(new NodoAVL(clave, dato, null, null));
            }
            nodo.recalcularAltura();
        } else if (clave.compareTo(nodo.getClave()) > 0) {
            // elemento es mayor a nodo.getElem()
            // Si tiene HD baja a la derecha, sino lo setea
            if (nodo.getDerecho() != null) {
                exito = insertarAux(nodo.getDerecho(), nodo, clave, dato);
               //NO VA balancear(nodo.getDerecho(), nodo);
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
            NodoAVL temporal= hijo.getIzquierdo();
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
        
}
