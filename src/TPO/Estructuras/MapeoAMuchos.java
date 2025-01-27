package TPO.Estructuras;
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

    public void asociar(String tipoDocumento, int numeroDocumento, String nombre, String apellido, String email) {
        int indice = funcionHash(tipoDocumento, numeroDocumento);
        NodoHashMapeoM actual = arreglo[indice];
        boolean exito=false;
        if (actual==null) {
            //si al principio del todo el nodo esta vacio
            NodoHashMapeoM nuevoNodo = new NodoHashMapeoM(tipoDocumento, numeroDocumento, nombre, apellido, email);
            arreglo[indice] = nuevoNodo; // Actualizar el arreglo
            cantidad++;
            exito=true;
        }
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
            nuevoNodo.setEnlace(arreglo[indice]); // Enlazar al inicio de la lista
            arreglo[indice] = nuevoNodo; // Actualizar el arreglo
            cantidad++;
            }
    }
    

}
