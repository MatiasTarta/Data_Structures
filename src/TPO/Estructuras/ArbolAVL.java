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
                balancear(nodo,raiz);
                nodo.recalcularAltura();
            }
            
            return exito;
        }
        
        public int getBalance(NodoAVL nodo) {
            if (nodo == null) {
                return 0;  // O -1, según la lógica que prefieras manejar en caso de un árbol vacío
            }
            
            int alturaIzquierda = (nodo.getIzquierdo() != null) ? nodo.getIzquierdo().getAltura() : -1;
            int alturaDerecha = (nodo.getDerecho() != null) ? nodo.getDerecho().getAltura() : -1;
            
            return alturaDerecha - alturaIzquierda;
        }
        
    
        public NodoAVL balancear(NodoAVL nodo,NodoAVL padre) {
            //if padre==null o ==raiz entonces el nodo que retorna es la nueva raiz
            if ( padre==null || nodo.getClave()==padre.getClave()) {
                padre=null;
                //si balanceo desde la raiz seteo a padre en null para que cuando retorne el nodo lo setee como nueva raiz
            }
            if (nodo != null) {
                int balance = getBalance(nodo);
                if (balance > 1) {  // Desbalanceado a la derecha
                    int balanceDerecho = getBalance(nodo.getDerecho());
                    
                    if (balanceDerecho >= 0) {  // Caso de rotación simple izquierda
                        nodo = rotacionSimpleIzquierda(nodo);
                    } else {  // Caso de rotación doble derecha-izquierda
                        nodo = rotacionDobleDerechaIzquierda(nodo);
                    }
                    if (padre==null) {
                        raiz=nodo;
                    }else{
                        padre.setDerecho(nodo);
                    }
                } else if (balance < -1) {  // Desbalanceado a la izquierda
                    int balanceIzquierdo = getBalance(nodo.getIzquierdo());
                    if (balanceIzquierdo <= 0) {  // Caso de rotación simple derecha
                        nodo = rotacionSimpleDerecha(nodo);
                    } else {  // Caso de rotación doble izquierda-derecha
                        nodo = rotacionDobleIzquierdaDerecha(nodo);
                    }
                    if (padre==null) {
                        raiz=nodo;
                    }else{
                        padre.setIzquierdo(nodo);
                    }
                }

                nodo.recalcularAltura();  // Recalcula la altura después de balancear
            }
            return nodo;  // Devuelve el nodo original o el nuevo raíz si hubo cambio
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
