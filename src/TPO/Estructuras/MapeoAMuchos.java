package TPO.Estructuras;
public class MapeoAMuchos {
    private int tamanio;
    private NodoHashMapeoM[] arreglo;
    private int cantidad;

    public MapeoAMuchos(int tamanio) {
        this.tamanio = tamanio;
        arreglo = new NodoHashMapeoM[this.tamanio];
        cantidad = 0;
    }

    private int funcionHash(Object clave) {
        return Math.abs(clave.hashCode()) % tamanio;
    }

    public boolean asociar(Object clave, Object objeto) {
        int indice = funcionHash(clave);
        NodoHashMapeoM actual = arreglo[indice];
        boolean existe = false;

        while (actual != null && !existe) {
            if (actual.getDominio().equals(clave)) {
                existe = true;
            } else {
                actual = actual.getEnlace();
            }
        }

        if (!existe) {
            NodoHashMapeoM nuevoNodo = new NodoHashMapeoM(clave, objeto);
            nuevoNodo.setEnlace(arreglo[indice]);
            arreglo[indice] = nuevoNodo;
            cantidad++;
        }

        return existe;
    }

    public boolean desasociar(Object clave) {
        int indice = funcionHash(clave);
        NodoHashMapeoM actual = arreglo[indice];
        NodoHashMapeoM previo = null;
        boolean eliminado = false;

        while (actual != null && !eliminado) {
            if (actual.getDominio().equals(clave)) {
                if (previo == null) {
                    arreglo[indice] = actual.getEnlace();
                } else {
                    previo.setEnlace(actual.getEnlace());
                }
                eliminado = true;
                cantidad--;
            }
            previo = actual;
            actual = actual.getEnlace();
        }

        return eliminado;
    }

    public Object obtenerValor(Object clave) {
        int indice = funcionHash(clave);
        NodoHashMapeoM actual = arreglo[indice];
        while (actual != null) {
            if (actual.getDominio().equals(clave)) {
                return actual.getRango();
            }
            actual = actual.getEnlace();
        }
        return null;
    }
}
