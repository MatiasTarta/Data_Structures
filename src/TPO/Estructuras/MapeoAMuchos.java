package TPO.Estructuras;

import lineales.dinamicas.Lista;

public class MapeoAMuchos {
    private int tamanio;
    private NodoHashMapeoM[] arreglo;
    private int cantidad;

    public MapeoAMuchos(int tamanio){
        this.tamanio=tamanio;
        arreglo= new NodoHashMapeoM[this.tamanio];
        cantidad=0;
    }

    private int funcionHash(String tipoDocumento, int numeroDocumento) {
        return Math.abs((tipoDocumento + numeroDocumento).hashCode()) % tamanio;
    }

    public boolean asociar(String tipoDocumento, int numeroDocumento, String nombre, String apellido, String email) {
        int indice = funcionHash(tipoDocumento, numeroDocumento);
        NodoHashMapeoM actual = arreglo[indice];
        boolean exito=false;
        
        // busca si ya existe el dominio (tupla de tipoDocumento y numeroDocumento)
        while (actual != null && !exito) {
            if (actual.getTipoDocumento().equals(tipoDocumento) && actual.getNumeroDocumento() == numeroDocumento) {
                // Si el dominio ya existe, agregar al rango si no está duplicado
                if (!actual.getRango().contiene(nombre) && !actual.getRango().contiene(apellido) && !actual.getRango().contiene(email)) {
                    actual.getRango().insertar(nombre, 1);
                    actual.getRango().insertar(apellido, 2);
                    actual.getRango().insertar(email, 3);
                    cantidad++;
                }
                exito=true;
            }else{
                //si no estoy en el nodo correcto busco al siguiente
                actual = actual.getEnlace();
            }
            
        }
            if (!exito) {
            //si recorri todos los nodos y no estaba creo un nodo nuevo
            NodoHashMapeoM nuevoNodo = new NodoHashMapeoM(tipoDocumento, numeroDocumento, nombre, apellido, email);
            nuevoNodo.setEnlace(arreglo[indice]); // si al principio no habia nada se setea el enlace en nulo
            arreglo[indice] = nuevoNodo; // Actualizar el arreglo
            cantidad++;
            }
            return exito;
    }

    public boolean desacoiar(String tipoDocumento,int numeroDocumento){
        boolean exito=false;
        int indice= funcionHash(tipoDocumento, numeroDocumento);
        NodoHashMapeoM actual=arreglo[indice];
        NodoHashMapeoM previo=null;
        while (actual!=null && !exito) {
            if (actual.getTipoDocumento().equals(tipoDocumento) && actual.getNumeroDocumento() == numeroDocumento){
                if (previo==null) {
                    //caso donde el elemento a eliminar esta primero
                    arreglo[indice]=actual.getEnlace();
                     exito=true;
                }else{
                    //caso donde el elemento a eliminar esta entre medio o al final
                    previo.setEnlace(actual.getEnlace());
                    exito=true;
                }
            }
            previo=actual;
            actual=actual.getEnlace();

        }
        return exito;
    }



    public boolean esVacio() {
        return cantidad == 0;
    }

     public Lista obtenerValor(String tipoDocumento, int numeroDocumento) {
        int indice = funcionHash(tipoDocumento, numeroDocumento);
        NodoHashMapeoM actual = arreglo[indice];
        Lista rangos=null;
        // Buscar el nodo del dominio
        while (actual != null) {
            if (actual.getTipoDocumento().equals(tipoDocumento) && actual.getNumeroDocumento() == numeroDocumento) {
                rangos= actual.getRango();
            }
            actual = actual.getEnlace();
        }

        // Si no se encuentra el dominio, devolver null
        return rangos;
    }

    


    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamanio; i++) {
            NodoHashMapeoM actual = arreglo[i];
            sb.append("Cubeta ").append(i).append(": ");
            while (actual != null) {
                sb.append(actual.getTipoDocumento()).append(" -> ").append(actual.getRango()).append(" | ");
                actual = actual.getEnlace();
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    

}
