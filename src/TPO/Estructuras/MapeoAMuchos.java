package TPO.Estructuras;

import lineales.dinamicas.Lista;
import TPO.SistemaMudanzas.Cliente;;
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
        return Math.abs((tipoDocumento + numeroDocumento).hashCode() - 1) % tamanio;
    }
    

    public boolean asociar(String tipoDocumento, int numeroDocumento, String nombre, String apellido, int telefono,String email) {
        int indice = funcionHash(tipoDocumento, numeroDocumento);
        NodoHashMapeoM actual = arreglo[indice];
        boolean exito=false;
        // busca si ya existe el dominio (tupla de tipoDocumento y numeroDocumento)
        while (actual != null && !exito) {
            if (actual.getTipoDocumento().equals(tipoDocumento) && actual.getNumeroDocumento() == numeroDocumento) {
                // Si el dominio ya existe, agregar al rango si no está duplicado
                if (!(actual.getRango().equivale(nombre, apellido, numeroDocumento,email))) {
                    actual.getRango().setNombre(nombre);
                    actual.getRango().setApellido(apellido);
                    actual.getRango().setTelefono(telefono);
                    actual.getRango().setEmail(email);
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
            NodoHashMapeoM nuevoNodo = new NodoHashMapeoM(tipoDocumento, numeroDocumento, nombre, apellido, telefono,email);
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
                    //si el elemento al final actualgetEnlace es nulo asi que el ultimo elemento se pierde
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

     public Cliente obtenerValor(String tipoDocumento, int numeroDocumento) {
        System.out.println(tipoDocumento);
        System.out.println(numeroDocumento);
        int indice = funcionHash(tipoDocumento.toUpperCase(), numeroDocumento);
        NodoHashMapeoM actual = arreglo[indice];
        Cliente valor= null;
        while (actual != null) {
            if (actual.getTipoDocumento().equals(tipoDocumento) && actual.getNumeroDocumento() == numeroDocumento) {
                valor=actual.getRango();
            }
            actual = actual.getEnlace();
        }
        return valor;
    }

    public Lista obtenerConduntoDominio() {
        Lista dominios = new Lista();
        for (int i = 0; i < arreglo.length; i++) {
            NodoHashMapeoM actual = arreglo[i];
            while (actual != null) {
                dominios.insertar(actual.getTipoDocumento() + ";" + actual.getNumeroDocumento(), dominios.longitud() + 1);
                actual = actual.getEnlace();
            }
        }
        return dominios;
    }
    

    public Lista obtenerConjuntoRangos(){
        Lista rangos= new Lista();
        for (int i = 0; i < arreglo.length; i++) {
            NodoHashMapeoM actual= arreglo[i];
            while (actual!=null) {
                rangos.insertar(actual.getRango(), rangos.longitud()+1);
                actual = actual.getEnlace();

            }
        }
        return rangos;
    }

    

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamanio; i++) {
            NodoHashMapeoM actual = arreglo[i];
            
            if (actual == null) {
                sb.append("----"); // Señalizar posición vacía
            } else {
                while (actual != null) {
                    // Mostrar tipo de documento y DNI dentro de corchetes
                    sb.append("[").append(actual.getTipoDocumento()).append(": ").append(actual.getNumeroDocumento()).append("]");
                    
                    if (actual.getEnlace() != null) {
                        sb.append(" -> "); // Flecha si hay un siguiente nodo
                    }
                    actual = actual.getEnlace(); // Avanzar al siguiente nodo
                }
            }
            sb.append("\n"); // Nueva línea después de procesar todos los nodos de esa cubeta
        }
        return sb.toString();
    }
    
    
    
    
    

}
