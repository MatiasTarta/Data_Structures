package TPO.Estructuras;
import lineales.dinamicas.Lista;
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

    public boolean eliminarArco(Object origen,Object destino){
        boolean exito=false;
        if (this.inicio!=null) {
            NodoVert origenAuxiliar= buscarVertice(origen);
            NodoVert destinoAuxiliar = buscarVertice(destino);
                      
          if (origenAuxiliar!=null && destinoAuxiliar!=null &&!origen.equals(destino)) {
            exito= eliminarArcoAux(origenAuxiliar,destino) && eliminarArcoAux(destinoAuxiliar,origen);
         }
        }
        return exito;
    }

    private boolean eliminarArcoAux(NodoVert origen,Object destino){
        boolean exito=false;
            if (origen.getPrimerAdy().getVertice().getElemento().equals(destino)) {
                origen.setPrimerAdy(origen.getPrimerAdy().getSigAdyacente());
                exito=true;
            }else{
                NodoAdy aux = origen.getPrimerAdy();
                while (aux.getSigAdyacente()!=null && !exito) {
                    if (aux.getSigAdyacente().getVertice().getElemento().equals(destino)) {
                        aux.setSigAdyacente(aux.getSigAdyacente().getSigAdyacente());
                        exito=true;
                    }else{
                        aux=aux.getSigAdyacente();
                    }
                }
            }

        return exito;
    }

    public boolean existeCamino(Object origen,Object destino){
        boolean exito=false;
        //verifica si ambos vertices existen
        NodoVert aux0=null;
        NodoVert auxD=null;
        NodoVert aux=this.inicio;

        while ((aux0==null || auxD==null)&& aux!=null) {
            if (aux.getElemento().equals(origen)) {
                aux0=aux;
            }
            if (aux.getElemento().equals(destino)) {
                auxD=aux;
            }
            aux=aux.getSigVertice();
        }
        if (aux0!=null && auxD!=null) {
            //si ambos vertices existen busca si existe camino entre ambos
            Lista visitados=new Lista();
            exito=existeCaminoAux(aux0, destino, visitados);
        }
        return exito;
    }

    private boolean existeCaminoAux(NodoVert nodo,Object destino,Lista visitados){
        boolean exito=false;
        if (nodo!=null) {
            if (nodo.getElemento().equals(destino)) {//caso base
                exito=true;
            }else{
                //si no es el destino verifica si hay camino entre n y destino
                visitados.insertar(nodo.getElemento(), visitados.longitud()+1);//marca el nodo actual
                NodoAdy ady = nodo.getPrimerAdy();//primer nodo adyacente a chequear
                while (!exito && ady!=null) {
                    if (visitados.localizar(ady.getVertice().getElemento())<0) {//si un nodo adyacente no fue visitado
                        exito= existeCaminoAux(ady.getVertice(), destino, visitados);
                    }
                }
            }
        }
        return exito;
    }
}
