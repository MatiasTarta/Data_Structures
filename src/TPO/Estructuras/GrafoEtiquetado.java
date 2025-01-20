package TPO.Estructuras;

public class GrafoEtiquetado {
    private NodoVert inicio;

    public GrafoEtiquetado(){
        this.inicio=null;
    }



    public boolean insertarVertice(Object elem){
        boolean exito =false;
        NodoVert aux= buscarVertice(elem);
        if (aux==null) {//si no esta en el grafo inserto el vertice en el inicio
            this.inicio= new NodoVert(elem, inicio);
            exito=true;
        }

        return exito;
    }

    private NodoVert buscarVertice(Object buscado){
        NodoVert aux = this.inicio;
        while (aux!=null && !(aux.getElemento().equals(buscado))) {
            //mientras haya nodos y no coincidan los elementos sigo buscando
            aux=aux.getSigVertice();
        }
        return aux;
    }

    public boolean existeVertice(Object elemento){
        return !(buscarVertice(elemento)==null);
    }

    public boolean eliminarVertice(Object elemento){
        boolean exito =false;
        if (this.inicio!=null) {
            if (this.inicio.getElemento().equals(elemento)) {
                eliminarVerticeAux(this.inicio.getPrimerAdy(),elemento);
                this.inicio=this.inicio.getSigVertice();
                exito=true;
            }else{
                NodoVert aux= this.inicio;
                while (aux!=null && !exito) {
                    if (aux.getSigVertice().equals(elemento)) {
                        eliminarVerticeAux(aux.getSigVertice().getPrimerAdy(),elemento);
                        aux.setSigVertice(aux.getSigVertice().getSigVertice());
                        exito=true;
                    }else{
                        aux=aux.getSigVertice();
                    }
                }
            }
        }
        return false;
    }

    private void eliminarVerticeAux(NodoAdy nodo,Object elemento){
        //elimina los arcos
        while (nodo!=null) {
            NodoAdy aux= nodo.getVertice().getPrimerAdy();
            if (aux.getVertice().getElemento().equals(elemento)) {
                nodo.getVertice().setPrimerAdy(aux.getSigAdyacente());
            }else{
                boolean paro=false;
                while (aux!=null && !paro) {
                    if (aux.getSigAdyacente().getVertice().getElemento().equals(elemento)) {
                        aux.setSigAdyacente(aux.getSigAdyacente().getSigAdyacente());
                        paro=true;
                    }else{
                        aux=aux.getSigAdyacente();
                    }
                }
            }
        }
    }

    public boolean insertarArco(Object origen,Object destino,double etiqueta){
        boolean exito=false;
        if (this.inicio!=null) {
            NodoVert origenAuxiliar= buscarVertice(origen);
            NodoVert destinoAuxiliar = buscarVertice(destino);
            if (origenAuxiliar!=null && destinoAuxiliar!=null && !(origen.equals(destino))) {
                origenAuxiliar.setPrimerAdy(new NodoAdy(destinoAuxiliar,origenAuxiliar.getPrimerAdy(), etiqueta));
                destinoAuxiliar.setPrimerAdy(new NodoAdy(origenAuxiliar, destinoAuxiliar.getPrimerAdy(), etiqueta));
                exito=true;
            }
        }
        return exito;
    }
}
