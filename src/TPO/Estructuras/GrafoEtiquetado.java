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

    public Lista caminoMasCorto(Object origen, Object destino) {
        //camino mas corto en terminos de nodos
        Lista visitados = new Lista();
        Lista res = new Lista();
        if (this.inicio != null) {
            NodoVert origenAux = buscarVertice(origen);
            NodoVert destinoAux = buscarVertice(destino);
            if (origenAux != null && destinoAux != null) {
                res = caminoMasCortoAux(origenAux, destino, visitados, res);
            }
        }
        return res;
    }
    private Lista caminoMasCortoAux(NodoVert vertice, Object destino, Lista visitados, Lista res) {
        if (vertice != null) {
            visitados.insertar(vertice.getElemento(), visitados.longitud() + 1);
            if (vertice.getElemento().equals(destino)) { // si vert es el destino, encontró un camino
                res = visitados.clone();
            } else {
                NodoAdy ady = vertice.getPrimerAdy();
                while (ady != null) {
                    if (visitados.localizar(ady.getVertice().getElemento()) < 0) {
                        if (res.esVacia() || res.longitud() > visitados.longitud()) { // que para seguir buscando un camino, no supere la longitud del anterior
                            res = caminoMasCortoAux(ady.getVertice(), destino, visitados, res); // llamado recursivo con el vecino
                        }
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            visitados.eliminar(visitados.longitud()); // a la vuelta lo elimina
        }
        return res;
    }

    public Lista caminoMenorDistancia(Object origen, Object destino) {
        Lista visitados = new Lista();
        Lista res = new Lista();
        double[] menosKM = new double[1];
        menosKM[0] = 0;
        if (this.inicio != null) {
            NodoVert origenAux = buscarVertice(origen);
            NodoVert destinoAux = buscarVertice(destino);
            if (origenAux != null && destinoAux != null) {
                res = caminoMenorDistanciaAux(origenAux, destino, 0, menosKM, visitados, res);
            }
        }
        return res;
    }
    
    private Lista caminoMenorDistanciaAux(NodoVert vertice, Object destino, double kmAux, double[] menosKM, Lista visitados, Lista res) {
        //Comienza en el vértice origen y explora recursivamente cada vértice adyacente, calculando la distancia acumulada.
        if (vertice != null) {
            double km = menosKM[0];
            if (km == 0 || km > kmAux) {//Se realiza una comparación entre las distancias acumuladas (kmAux) para mantener la mejor distancia
                visitados.insertar(vertice.getElemento(), visitados.longitud() + 1);
                if (vertice.getElemento().equals(destino)) {
                    menosKM[0] = kmAux;
                    res = visitados.clone();
                } else {
                    NodoAdy ady = vertice.getPrimerAdy();
                    while (ady != null) {
                        if (visitados.localizar(ady.getVertice().getElemento()) < 0) {
                            double aux = kmAux + ady.getEtiqueta();
                            res = caminoMenorDistanciaAux(ady.getVertice(), destino, aux, menosKM, visitados, res);
                        }
                        ady = ady.getSigAdyacente();
                    }
                }
                visitados.eliminar(visitados.longitud());
            }
        }
        return res;
    }

    
    
    
    public Lista caminoSinRepetir(Object origen, Object destino, Object intermedio) {
        Lista caminos = new Lista();  // Lista para almacenar todos los caminos
        Lista visitados = new Lista();  // Lista para marcar los vértices visitados
        if (this.inicio != null) {
            NodoVert origenAux = buscarVertice(origen);
            NodoVert destinoAux = buscarVertice(destino);
            NodoVert intermedioAux = buscarVertice(intermedio);
            if (origenAux != null && destinoAux != null && intermedioAux != null) {
                caminoSinRepetirAux(origenAux, destino, intermedio, caminos, visitados, false);
            }
        }
        return caminos;  // Devolver todos los caminos encontrados
    }
    
    private void caminoSinRepetirAux(NodoVert vertice, Object destino, Object intermedio, 
                                     Lista caminos, Lista visitados, Boolean pasoInter) {
        if (vertice != null) {
            visitados.insertar(vertice.getElemento(), visitados.longitud() + 1);
    
            if (vertice.getElemento().equals(intermedio)) {
                pasoInter = true;  // Marca que se pasó por el nodo intermedio
            }
    
            if (vertice.getElemento().equals(destino) && pasoInter) {
                // Si se ha llegado al destino y se ha pasado por el nodo intermedio
                caminos.insertar(visitados.clone(), caminos.longitud() + 1);
            } else {
                // Explora los adyacentes
                NodoAdy ady = vertice.getPrimerAdy();
                while (ady != null) {
                    if (visitados.localizar(ady.getVertice().getElemento()) < 0) {
                        caminoSinRepetirAux(ady.getVertice(), destino, intermedio, caminos, visitados, pasoInter);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
    
            visitados.eliminar(visitados.longitud());  // Desmarca el vértice actual
        }
    }
    

    public Lista caminoMayorDistancia(Object origen, Object destino, double maximo) {
        Lista visitados = new Lista();
        Lista res = new Lista();
        double[] mayorKM = new double[1];
        mayorKM[0] =0;
        if (this.inicio != null) {
            NodoVert origenAux = buscarVertice(origen);
            NodoVert destinoAux = buscarVertice(destino);
            if (origenAux != null && destinoAux != null) {
                res = caminoMayorDistanciaAux(origenAux, destino, 0, mayorKM, visitados, res, maximo);
            }
        }
        return res;
    }
    
    private Lista caminoMayorDistanciaAux(NodoVert vertice, Object destino, double kmAux, double[] mayorKM, Lista visitados, Lista res, double maximo) {
        if (vertice != null) {
            if (!(kmAux > maximo)){//si la distancia todavia no acumula el maximo
                visitados.insertar(vertice.getElemento(), visitados.longitud() + 1);
            // si llegamos al destino y la distancia es mayor que la mejor conocida, lo guardamos
            if (vertice.getElemento().equals(destino) && kmAux <= maximo) {
                if (kmAux > mayorKM[0]) {
                    mayorKM[0] = kmAux;
                    res = visitados.clone();
                }
            } else {
                NodoAdy ady = vertice.getPrimerAdy();
                while (ady != null) {
                    if (visitados.localizar(ady.getVertice().getElemento()) < 0) {
                        res = caminoMayorDistanciaAux(ady.getVertice(), destino, kmAux + ady.getEtiqueta(), mayorKM, visitados, res, maximo);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            visitados.eliminar(visitados.longitud());
            }
            
        }
        return res;
    }

    public Lista ciudadesIntermedias(Object origen, Object destino) {
        Lista camino = caminoMasCorto(origen, destino);
        // Si la lista de camino no está vacía, eliminamos el primer y el último elemento
        if (!camino.esVacia()) {
            camino.eliminar(1); // Eliminar el primer nodo (origen)
            camino.eliminar(camino.longitud()); // Eliminar el último nodo (destino)
        }
        return camino;
    }
    

    
    

    
    public String toString() {
        String resultado;
        if (this.inicio != null) {
            resultado = toStringAux(this.inicio);
        } else {
            resultado = "El grafo esta vacio ¿?";
        }
        return resultado;
    }

    private String toStringAux(NodoVert vertice) {
        String cadena = "";
        if (vertice != null) {
            cadena = cadena + vertice.getElemento().toString() + "<------>";
            NodoAdy ady = vertice.getPrimerAdy();
            while (ady != null) {
                if (ady.getSigAdyacente() != null) {
                    cadena = cadena + ady.getVertice().getElemento().toString() + "(" + ady.getEtiqueta() + ")" + "<------>";
                } else {
                    cadena = cadena + ady.getVertice().getElemento().toString() + "(" + ady.getEtiqueta() + ")";
                }
                ady = ady.getSigAdyacente();
            }
            cadena = cadena + "\n" + toStringAux(vertice.getSigVertice());
        }
        return cadena;
    }

    public int cantidadNodos() {
        int contador = 0;
        NodoVert actual = this.inicio;
    
        while (actual != null) {
            contador++;
            actual = actual.getSigVertice();
        }
        return contador;
    }


}
