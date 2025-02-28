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

    public boolean esVacio() {
        return cantidad == 0;
    }

    public Lista obtenerConjuntoDominio() {
        Lista dominios = new Lista();
        for (int i = 0; i < arreglo.length; i++) {
            NodoHashMapeoM actual = arreglo[i];
            while (actual != null) {
                dominios.insertar(actual.getDominio(), dominios.longitud() + 1);
                actual = actual.getEnlace();
            }
        }
        return dominios;
    }

    public Lista obtenerConjuntoRangos() {
        Lista rangos = new Lista();
        for (int i = 0; i < arreglo.length; i++) {
            NodoHashMapeoM actual = arreglo[i];
            while (actual != null) {
                rangos.insertar(actual.getRango(), rangos.longitud() + 1);
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
                sb.append("----");
            } else {
                while (actual != null) {
                    sb.append("[").append(actual.getDominio()).append("]");
                    if (actual.getEnlace() != null) {
                        sb.append(" -> ");
                    }
                    actual = actual.getEnlace();
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
