package conjuntistas;

public class ArbolABB {
    private NodoArbol raiz;

    public ArbolABB(){
        this.raiz=null;
    }    
    

    public boolean esVacio(){
        return this.raiz== null;
    }

    public boolean insertar(Comparable elemNuevo){
        boolean exito=false;
        if(esVacio()){
            this.raiz= new NodoArbol(elemNuevo, null, null);
        }else{
            exito=insertarAux(elemNuevo,this.raiz);
        }
        return exito;
    }
    public boolean insertarAux(Comparable elemNuevo, NodoArbol n){
        int compareTo= elemNuevo.compareTo(n.getElem());
        boolean exito=false;

        if(compareTo!=0){
            if(compareTo<0){
                //elemento menor que n.getElem()
                //si tiene HI baja a la izquierda, sino agrega elemento
                if(n.getIzquierdo()!=null){
                    exito=insertarAux(elemNuevo, n.getIzquierdo());
                }else{
                    n.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                }
            }else{
                //elemento es mayor que n.getElem()
                //si tiene HD baja a la derecha, sino agrega elemento
                if(n.getDerecho()!=null){
                    exito=insertarAux(elemNuevo, n.getDerecho());
                }else{
                    n.setDerecho(new NodoArbol(elemNuevo, null, null));
                }
            }
        }else{
            exito=false;
            //error de elemento repetido
        }
        return exito;
    }

    

}
