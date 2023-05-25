package jerarquicas;
import lineales.dinamicas.*;
public class ArbolBin {
    private NodoArbol raiz;
    public ArbolBin(){
        this.raiz=null;
    }
    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar){
        boolean exito=false;
        if(this.raiz==null){
            //si el arbol esta vacio pone elemNuevo en la raiz
            this.raiz=new NodoArbol(elemNuevo, null, null);
        }else{
            //si el arbol no esta vacio busca al padre
            NodoArbol nPadre=obtenerNodo(this.raiz,elemPadre);
            //si el padre existe y lugar no esta ocupado lo pone, si no da error
            if(nPadre!=null){
                if(lugar=='I'&&nPadre.getIzquierdo()==null){
                    nPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                }else if(lugar=='D'&&nPadre.getDerecho()==null){
                    nPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                }else{
                    exito=false;
                }
            }else{
                exito=false;
            }
        }
        return exito;
    }
    private NodoArbol obtenerNodo(NodoArbol n,Object buscado){
        //metodo PRIVADO, busca un elem y devuelve el nodo que lo contiene, o de lo contrario null
        NodoArbol resultado=null;
        if(n!=null){
            if(n.getElem().equals(buscado)){
                resultado=n;
            }else{
                //si no es el buscado, busca primero en Hijo Izquierdo
                resultado=obtenerNodo(n.getIzquierdo(), buscado);
                //si no lo encontro en hijo izquierdo busca en hijo derecho
                if(resultado==null){
                    resultado=obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }
    public int alturaAux(NodoArbol nodoActual){
        int alturaIzquierdo;
        int alturaDerecho;
            if(nodoActual==null){
                return -1;
                //caso base, altura=0 entonces llegamos a una hoja
            }
        alturaIzquierdo=alturaAux(nodoActual.getIzquierdo());
        //paso recursivo, consigo la altura del hijo izquierdo de mi nodo actual
        alturaDerecho=alturaAux(nodoActual.getDerecho());
        return Math.max(alturaIzquierdo,alturaDerecho)+1;
        //retorna la altura +1(caso base=0 entonces altura padre de la hoja(caso base)=0+1)
    }
    public int altura(){
        return alturaAux(this.raiz);
    }

    public int nivel(Object buscado){
        //devuelve el nivel dodne se ecneuntra el nodo que buscamos
        int niv=-1;
        //inicializa en -1
            if(this.raiz!=null){
                //si mi raiz no es nula entra
                niv= nivelAux(this.raiz, buscado,0);
            }
            return niv;   
    }

    private int nivelAux(NodoArbol nodoAux,Object buscado,int profundidad){
        int nivel=-1;   
        if(nodoAux!=null){
            //condicion base 1 que mi nodo exista
            if(nodoAux.getElem()!=buscado){
                //condicion base 2 que el element que busco todavia no haya sido encontrado
                nivel= nivelAux(nodoAux.getIzquierdo(), buscado, profundidad+1);
                //pruebo or el lado izquierdo
                if(nivel==-1){
                    //si devuelve -1, en el lado izquierdo no estaba, me voy parael derecho
                    nivel=nivelAux(nodoAux.getDerecho(), buscado, profundidad+1);
                }
            }else{
                //si lo encuentro devuelvo la profundidad que fui iterand en cada llamado
                nivel=profundidad;
            }
        }
        return nivel;
    }

    public Object padre(Object elemento){
        // Dado un elemento devuelve el valor almacenado en su nodo padre (busca la primera aparición de elemento).
        Object resultado = null;
        if (this.raiz != null) {
            if (this.raiz.getElem() != elemento) {
                resultado = padreAux(elemento,this.raiz);
            }
        }
        return resultado;

    }
    //commit padre
    private Object padreAux(Object elemento, NodoArbol nodoAux){
        Object resultado=null;
        if(nodoAux!=null){
            if(nodoAux.getIzquierdo()!=null && resultado==null){
                 if((nodoAux.getIzquierdo().getElem() == (elemento))) {
                    resultado = nodoAux.getElem();
                }
            }
            if(nodoAux.getDerecho()!=null && resultado==null){
                if(nodoAux.getIzquierdo().getElem()==elemento){
                    resultado=nodoAux.getElem();
                }
            }
            if(resultado==null){
                resultado=padreAux(elemento, nodoAux.getDerecho());
                if(resultado==null){
                    resultado=padreAux(elemento, nodoAux.getIzquierdo());
                }
            }
        }
        return resultado;
    }


    public boolean esVacio(){
        return this.raiz==null;
    }

    public String toString() {
        String cad = " ";
        if (this.raiz == null) {
            cad = "El arbol esta vacio";
        } else {
            cad = toStringAux(this.raiz);
        }
        return cad;
    }

    private String toStringAux(NodoArbol nodo) {
        String mensaje = "";
        if (nodo != null) {
            mensaje = "\n NODO: " + nodo.getElem() + mensaje;
            if (nodo.getIzquierdo() != null) {
                mensaje = mensaje + " HI:" + (nodo.getIzquierdo().getElem());
            } else {
                mensaje = mensaje + " HI: - ";
            }
            if (nodo.getDerecho() != null) {
                mensaje = mensaje + (" HD:" + nodo.getDerecho().getElem());
            } else {
                mensaje = mensaje + " HD: - ";
            }
        }

        if (nodo.getIzquierdo() != null) {
            mensaje = mensaje + toStringAux(nodo.getIzquierdo());
        }

        if (nodo.getDerecho() != null) {
            mensaje = mensaje + toStringAux(nodo.getDerecho());
        }

        return mensaje;
    }

    public Lista listarPreorden(){
        Lista lis= new Lista();
        listarPreordenAux(this.raiz,lis);
        return lis;
    }
    private void listarPreordenAux(NodoArbol nodo, Lista lis){
        if(nodo!=null){
            //visita el elemento en el nodo
            lis.insertar(nodo.getElem(), lis.longitud()+1);
            listarPreordenAux(nodo.getIzquierdo(), lis);
            listarPreordenAux(nodo.getDerecho(), lis);
        }
    }
        public Lista listarInorden(){
            Lista lis= new Lista();
            listarInordenAux(this.raiz,lis);
            return lis;
        }
        private void listarInordenAux(NodoArbol nodo, Lista lis){
            if (nodo != null) {
                listarInordenAux(nodo.getIzquierdo(), lis);
                lis.insertar(nodo.getElem(), lis.longitud() + 1);
                listarInordenAux(nodo.getDerecho(), lis);
            }
        }
        public Lista listarPosorden(){
            Lista lis= new Lista();
            listarPosordenAux(this.raiz,lis);
            return lis;
        }
        private void listarPosordenAux(NodoArbol nodo, Lista lis){
            if(nodo!=null){
                listarPosordenAux(nodo.getIzquierdo(), lis);
                listarPosordenAux(nodo.getDerecho(), lis);
                lis.insertar(nodo.getElem(), lis.longitud() + 1);
            }
        }
        public Lista listarNiveles(){
            Lista lis= new Lista();
            lis.insertar(this.raiz.getElem(),  1);
            listarNivelesAux(this.raiz,lis);
            return lis;
        }
        private void listarNivelesAux(NodoArbol nodo, Lista lis){
            
            if(nodo != null){
                
                if (nodo.getIzquierdo() != null){
                    lis.insertar((nodo.getIzquierdo()).getElem(), lis.longitud() + 1);
                }
                if (nodo.getDerecho() != null){
                    lis.insertar((nodo.getDerecho()).getElem(), lis.longitud() + 1);
                }
                listarNivelesAux(nodo.getIzquierdo(), lis);
                listarNivelesAux(nodo.getDerecho(), lis);
            }
        }
        public ArbolBin clonar( ){
            ArbolBin arbolClon = new ArbolBin();
            if(!esVacio()){
                arbolClon.raiz=clonarAux(raiz);
            }
            return arbolClon;
        }
        private NodoArbol clonarAux(NodoArbol nodo){
            if(nodo==null){
                return null;
            }
            NodoArbol nodoClon=new NodoArbol(nodo.getElem(), (clonarAux(nodo.getIzquierdo())), (clonarAux(nodo.getDerecho())));
            return nodoClon;
        }
        public void vaciar(){
            this.raiz=null;
        }
        public boolean menorCantApariciones(Object elem,int cant){
            return(contarAparciones(elem,this.raiz,cant,0)<cant);
        }
        private int contarAparciones(Object x,NodoArbol aux, int cant, int contador){
            int contadorI=0, contadorD=0;
            if(aux!=null && contador<cant){
                if(aux.getElem().equals(x)){
                    contador++;
                }
                contadorI= contarAparciones(x, aux.getIzquierdo(), cant, 0);
                contadorD= contarAparciones(x, aux.getDerecho(), cant, 0);
                contador=contadorD+contadorI;
            }
            return contador;
        }
  

}
