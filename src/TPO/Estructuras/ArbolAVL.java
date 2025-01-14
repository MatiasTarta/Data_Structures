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

    public boolean insertar(Comparable clave, Object dato){
        boolean exito=true;
       
        if(this.raiz==null){
            raiz= new NodoAVL(clave, dato, null, null);
        }else{
            exito= insertarAux(raiz,clave,dato,null);
            raiz.recalcularAltura();
        }
        return exito;
    }

        public boolean insertarAux(NodoAVL nodo, Comparable clave, Object dato, NodoAVL padre) {
            //nodo no es nulo
            boolean exito=true;
            if (nodo == null) {
                //si llego aqui es que estoy en la posicion de insercion
                nodo = new NodoAVL(clave, dato, null, null);
                if (clave.compareTo(padre.getClave()) > 0) {
                    padre.setDerecho(nodo);
                } else if (clave.compareTo(padre.getClave()) < 0) {
                    padre.setIzquierdo(nodo);
                }
                exito=true;
            }
            
            if (clave.compareTo(nodo.getClave()) == 0) {
                exito= false;  // El elemento ya esta en el árbol
            } else if (clave.compareTo(nodo.getClave()) > 0) {
                exito= insertarAux(nodo.getDerecho(), clave, dato, nodo);
            } else {
                exito=  insertarAux(nodo.getIzquierdo(), clave, dato, nodo);
            }
            if (exito) {
                nodo.recalcularAltura();
                balancear(nodo);
                nodo.recalcularAltura();
            }
            
            return exito;
        }
        
        private int getBalance(NodoAVL nodo) {
            int alturaIzquierda = -1;
            int alturaDerecha = -1;
        
            if (nodo != null) {
                if (nodo.getIzquierdo() != null) {
                    alturaIzquierda = nodo.getIzquierdo().getAltura();
                }
        
                if (nodo.getDerecho() != null) {
                    alturaDerecha = nodo.getDerecho().getAltura();
                }
            }
            return alturaDerecha - alturaIzquierda;
        }
        
    
    
    
        public NodoAVL balancear(NodoAVL nodo) {
            if (nodo != null) {
                int balance = getBalance(nodo);
                if (balance > 1) {// Desbalanceado a la derecha
                    int balanceDerecho = getBalance(nodo.getDerecho());
                    if (balanceDerecho >= 0) { // Caso de rotación simple izquierda
                        nodo = rotacionSimpleIzquierda(nodo);
                    } else { // Caso de rotación doble derecha-izquierda
                        nodo = rotacionDobleDerechaIzquierda(nodo);
                    }
                }
                if (balance < -1) {// Desbalanceado a la izquierda
                    int balanceIzquierdo = getBalance(nodo.getIzquierdo());
                    if (balanceIzquierdo <= 0) { // Caso de rotación simple derecha
                        nodo = rotacionSimpleDerecha(nodo);
                    } else { // Caso de rotación doble izquierda-derecha
                        nodo = rotacionDobleIzquierdaDerecha(nodo);
                    }
                }
        
                nodo.recalcularAltura(); // Recalcula la altura después de balancear
            }
            return nodo; // Devuelve la nueva raíz del subárbol balanceado
        }
        
    

    public NodoAVL rotacionSimpleIzquierda(NodoAVL pivote) {
        NodoAVL hijo = pivote.getDerecho();
        NodoAVL temporal= hijo.getIzquierdo();
        pivote.setDerecho(hijo.getIzquierdo());
        hijo.setIzquierdo(pivote);
        pivote.setDato(temporal);
        hijo.recalcularAltura();
        pivote.recalcularAltura();
        return hijo;  // nueva raíz del subárbol
    }

    public NodoAVL rotacionSimpleDerecha(NodoAVL pivote){
        NodoAVL hijo= pivote.getIzquierdo();
        NodoAVL temporal= hijo.getDerecho();
        hijo.setDerecho(pivote);
        pivote.setIzquierdo(temporal);
        hijo.recalcularAltura();
        pivote.recalcularAltura();
        return hijo;
    }

    public NodoAVL rotacionDobleIzquierdaDerecha(NodoAVL pivote) {
        pivote.setIzquierdo(rotacionSimpleIzquierda(pivote.getIzquierdo()));
        return rotacionSimpleDerecha(pivote);
    }

    public NodoAVL rotacionDobleDerechaIzquierda(NodoAVL pivote) {
        pivote.setDerecho(rotacionSimpleDerecha(pivote.getDerecho()));
        return rotacionSimpleIzquierda(pivote);
    }

    public boolean eliminar(Comparable clave,NodoAVL nodo){
        //nodo ingresa como raiz
        boolean exito=true;
        
        

        return exito;
    }
    

    public String toString() {
        if (raiz == null) {
            return "Árbol vacío";
        }
        StringBuilder sb = new StringBuilder();
        toStringTree(raiz, sb, 0, true, "");
        return sb.toString();
    }
    
    private void toStringTree(NodoAVL nodo, StringBuilder sb, int depth, boolean isLeft, String prefix) {
        if (nodo != null) {
            sb.append(prefix);
            sb.append(isLeft ? "└── " : "├── ");
            sb.append(nodo.getClave()).append("\n");
    
            String childPrefix = prefix + (isLeft ? "    " : "│   ");
            toStringTree(nodo.getIzquierdo(), sb, depth + 1, false, childPrefix);
            toStringTree(nodo.getDerecho(), sb, depth + 1, true, childPrefix);
        }
    }

    

    
    
    
}
