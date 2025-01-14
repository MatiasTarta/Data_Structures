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
        int resultado = 0;
        if (nodo != null) {
            int alturaIzquierda = (nodo.getIzquierdo() != null) ? nodo.getIzquierdo().getAltura() : -1;
            int alturaDerecha = (nodo.getDerecho() != null) ? nodo.getDerecho().getAltura() : -1;
            resultado = alturaDerecha - alturaIzquierda;
        }
        return resultado;
    }
    
    
    
    public void balancear(NodoAVL nodo) {
        if (nodo != null) {  // Añadimos esta condición para evitar NPE
            int balance = getBalance(nodo);
    
            if (balance > 1) {  // Desbalanceado a la derecha
                int balanceDerecho = getBalance(nodo.getDerecho());
                if (balanceDerecho == 1 || balanceDerecho == 0) {
                    nodo = rotacionSimpleDerecha(nodo);
                } else if (balanceDerecho == -1) {
                    nodo = rotacionDobleIzquierdaDerecha(nodo);
                }
            } else if (balance < -1) {  // Desbalanceado a la izquierda
                int balanceIzquierdo = getBalance(nodo.getIzquierdo());
                if (balanceIzquierdo == -1 || balanceIzquierdo == 0) {
                    nodo = rotacionSimpleIzquierda(nodo);
                } else if (balanceIzquierdo == 1) {
                    nodo = rotacionDobleDerechaIzquierda(nodo);
                }
            }
    
            nodo.recalcularAltura();
            balancear(nodo.getDerecho());
            balancear(nodo.getIzquierdo());
        }
    }
    

    public NodoAVL rotacionSimpleIzquierda(NodoAVL pivote) {
        NodoAVL hijo = pivote.getDerecho();
        pivote.setDerecho(hijo.getIzquierdo());
        hijo.setIzquierdo(pivote);
        return hijo;  // nueva raíz del subárbol
    }

    public NodoAVL rotacionSimpleDerecha(NodoAVL pivote){
        NodoAVL hijo= pivote.getIzquierdo();
        NodoAVL temporal= hijo.getDerecho();
        hijo.setDerecho(pivote);
        pivote.setIzquierdo(temporal);
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
        StringBuilder sb = new StringBuilder();
        if (raiz != null) {
            toStringAux(raiz, sb, "", true);
        } else {
            sb.append("Árbol vacío");
        }
        return sb.toString();
    }
    
    private void toStringAux(NodoAVL nodo, StringBuilder sb, String prefix, boolean isLeft) {
        if (nodo != null) {
            sb.append(prefix);
            sb.append(isLeft ? "├── " : "└── ");
            sb.append(nodo.getClave()).append("\n");
    
            if (nodo.getIzquierdo() != null) {
                toStringAux(nodo.getIzquierdo(), sb, prefix + (isLeft ? "│   " : "    "), true);
            }
            if (nodo.getDerecho() != null) {
                toStringAux(nodo.getDerecho(), sb, prefix + (isLeft ? "│   " : "    "), false);
            }
        }
    }
    
    
}
