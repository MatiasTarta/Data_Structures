package TPO.Estructuras;

public class Diccionario {
    NodoAVL raiz;

    public Diccionario(){
        raiz= null;
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
        boolean estaIncluido= pertenece(raiz, clave);//si el elemento ya esta en el arbol no lo inserta de nuevo
        if(this.raiz==null){
            raiz= new NodoAVL(clave, dato, null, null);
        }else{
            exito= insertarAux(raiz,clave,dato,null);
            raiz.recalcularAltura();
        }
        return exito;
    }

    public boolean insertarAux(NodoAVL nodo, Comparable clave, Object dato, NodoAVL padre) {
        boolean exito = true;
    
        if (nodo == null) {
            // Si es nulo, entonces estoy a la posicion de insercion
            nodo = new NodoAVL(clave, dato, null, null);
            if (clave.compareTo(nodo.getClave()) > 0) {
                // Va al arbol derecho
                padre.setDerecho(nodo);
            } else if (clave.compareTo(nodo.getClave()) < 0) {
                padre.setIzquierdo(nodo);
            }
    
            return true;
        }
    
        if (clave.compareTo(nodo.getClave()) == 0) {
            exito = false;  // elemento ya esta en el arbol
        } else if (clave.compareTo(nodo.getClave()) > 0) {
            exito = insertarAux(nodo.getDerecho(), clave, dato, nodo);
        } else if (clave.compareTo(nodo.getClave()) < 0) {
            exito = insertarAux(nodo.getIzquierdo(), clave, dato, nodo);
        }

        if (exito) {
            nodo.recalcularAltura();
            balancear(raiz);
            nodo.recalcularAltura();
        }
    
        return exito;
    }

    private int getBalance(NodoAVL nodo) {
        int resultado=0;
        if (nodo!=null) {
            int alturaIzquierda =  nodo.getIzquierdo().getAltura();
        int alturaDerecha =  nodo.getDerecho().getAltura();
        //factor de equilibrio: alturaDerecha - alturaIzquierda
        resultado= alturaDerecha - alturaIzquierda;
        }
        return resultado;
    }
    
    
    public void balancear(NodoAVL nodo){
        //precondicion nodo distinto de null

        int balance= getBalance(nodo);

        if (balance>1) {//desbalanceado a la derecha
            int balanceDerecho= getBalance(nodo.getDerecho());
            if(balanceDerecho==1 || balanceDerecho ==0){
                nodo= rotacionSimpleDerecha(nodo);
            }else if (balanceDerecho==-1) {
                //rotacion doble izquierda-derecha
            }
        }else if( balance<-1){ //desbalanceado a la izqueirda
            int balanceIzquierdo= getBalance(nodo.getIzquierdo());
          if (balanceIzquierdo==-1 || balanceIzquierdo==0) {
            nodo= rotacionSimpleIzquierda(nodo);
          }else if(balanceIzquierdo==1){
            //rotacion doble derecha izquierda
          }
        }

        nodo.recalcularAltura();
        balancear(nodo.getDerecho());
        balancear(nodo.getIzquierdo());
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
    
}
