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
            
        }
    
        return exito;
    }

    private int getBalance(NodoAVL nodo) {
        if (nodo == null) return 0;
        int alturaIzquierda =  nodo.getIzquierdo().getAltura();
        int alturaDerecha =  nodo.getDerecho().getAltura();
        //factor de equilibrio: alturaDerecha - alturaIzquierda
        return alturaDerecha - alturaIzquierda;
    }
    
    
    public void balancear(NodoAVL nodo, NodoAVL padre){

    }


}
