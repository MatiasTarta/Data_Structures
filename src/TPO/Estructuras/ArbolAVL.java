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
        boolean exito=false;
        if(this.raiz!=null){
            exito= insertarAux(raiz,clave,dato,null);
        }else{
            raiz= new NodoAVL(clave, dato, null, null);
        }
        return exito;
    }

        public boolean insertarAux(NodoAVL nodo, Comparable clave, Object dato, NodoAVL padre) {
            //nodo no es nulo
            boolean exito=false;
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
        
    
        public void balancear(NodoAVL nodo,NodoAVL padre) {
            int balanceNodo= getBalance(nodo);
            int balanceHijo;
            if (balanceNodo>1) {//desbalance a la derecha
                balanceHijo= getBalance(nodo.getIzquierdo());
                if (balanceHijo==1 || balanceHijo==0) {
                    if (padre==null) {
                        this.raiz=rotacionSimpleDerecha(nodo);
                    }else{
                        padre.setIzquierdo(rotacionSimpleDerecha(nodo));
                    }
                }else if(balanceHijo==-1){
                    //rotacio doble izquierda derecha
                    if (padre==null) {
                        this.raiz=rotacionDobleIzquierdaDerecha(nodo);
                    }else{
                        padre.setIzquierdo(rotacionDobleIzquierdaDerecha(nodo));
                    }
                }else{
                    //si no cumple con ninguno de los casos tengo que balancear todo el subArbol.
                    balancear(nodo.getIzquierdo(), nodo);
                    balancear(nodo, padre);
                }
            }else if(balanceNodo<-1){//desbalance a la izquierda
                balanceHijo=getBalance(nodo.getDerecho());
                if (balanceHijo==-1 || balanceHijo==0) {
                    if (padre==null) {
                        this.raiz=rotacionSimpleIzquierda(nodo);
                    }else{
                        padre.setDerecho(rotacionSimpleIzquierda(nodo));
                    }
                }else if(balanceHijo==1){
                    //rotacion doble DI
                    if (padre==null) {
                        this.raiz=rotacionDobleDerechaIzquierda(nodo);
                    }else{
                        padre.setDerecho(rotacionDobleDerechaIzquierda(nodo));
                    }
                }else{
                    //si no cumple todo el subarbol derecho esta desbalanceado
                    balancear(nodo.getDerecho(), nodo);
                    balancear(nodo, padre);
                }
            }
        }
        
        
    

    public NodoAVL rotacionSimpleIzquierda(NodoAVL pivote) {

        NodoAVL hijo = pivote.getDerecho();
        NodoAVL temporal= hijo.getIzquierdo();
        hijo.setIzquierdo(pivote);
        pivote.setDerecho(temporal);
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
