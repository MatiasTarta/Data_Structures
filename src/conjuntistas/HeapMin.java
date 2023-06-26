package conjuntistas;

public class HeapMin {
    private int tamanio = 20;
    private Comparable[] heap;
    private int ultimo;

    // minimo
    public HeapMin() {
        this.heap = new Comparable[tamanio];
        this.ultimo = 0;
    }

    // Arreglo hijo izquierdo , posicion 2n
    // Arreglo hijo derecho , posicion 2n + 1
    // Padre , n/2
    public boolean insertar(Comparable aux) {
        boolean exito = false;
        if (esLLeno()) {
            aumentarArreglo();
        }
        exito = true;
        ultimo++;
        heap[ultimo] = aux;
        hacerSubir(ultimo);

        return exito;
    }

    private void aumentarArreglo() {
        tamanio = tamanio + 10;
        Comparable[] temp = new Comparable[tamanio];
        int longi = heap.length;
        for (int i = 0; i < longi; i++) {
            temp[i] = heap[i];
        }
        heap = temp;
    }

    private void hacerSubir(int posHijo) {
        int posPadre;
        Comparable temp = this.heap[posHijo];
        boolean salir = false;
        while (!salir) {
            posPadre = posHijo / 2;
            if (posPadre >= 1) {
                if ((this.heap[posPadre].compareTo(temp)) > 0) {
                    // intercambio
                    this.heap[posHijo] = this.heap[posPadre];
                    this.heap[posPadre] = temp;
                    posHijo = posPadre;
                } else {
                    salir = true;
                }
            } else {
                salir = true;
            }
        }
    }

    public boolean eliminarCima() {
        boolean exito = true;
        if (this.ultimo == 0) {
            // estructura vacia
            exito = false;
        } else {
            // saca la raiz y pone la ultima hoja en su lugar
            this.heap[1] = this.heap[ultimo];
            this.ultimo--;
            // reestablece la propuedad de heap minimo
            hacerBajar(1);
            exito = true;
        }
        return exito;
    }

    private void hacerBajar(int posPadre) {
        int posH;
        Comparable temp = this.heap[posPadre];
        boolean salir = false;
        while (!salir) {
            posH = posPadre * 2;
            // se calcula posicion del hijo izquierdo
            if (posH <= this.ultimo) {
                // temp tiene al menos un hijo izq y lo considera menor
                if (posH < this.ultimo) {
                    // hijo menor tiene hermano derecho
                    if ((this.heap[posH + 1].compareTo(this.heap[posH])) < 0) {
                        // el hijo derecho es el menor de los dos
                        posH++;
                    }
                }
                // compara al hijo menor con el padre
                if ((this.heap[posH].compareTo(temp)) < 0) {
                    // el hijo es menjor que el padre, los intercambia
                    this.heap[posPadre] = this.heap[posH];
                    this.heap[posH] = temp;
                    posPadre = posH;
                } else {
                    // el padre es menor q sus hijos, esta bien ubicado
                    salir = true;
                }
            } else {
                // el temp es hoja, esta bien ubicado
                salir = true;
            }
        }
    }

    public Comparable recuperarCima(){
        Comparable aux=null;
        if(!esVacio()){
            aux=this.heap[1];
        }
        return aux;
    }
    public void vaciar() {
        this.heap = new Comparable[tamanio];
        this.ultimo = 0;
    }
    
    

    public boolean esVacio() {
        return ultimo == 0;
    }

    public boolean esLLeno() {
        return ultimo == heap.length - 1;
    }

    public String toString() {
        String cadena = "";
        for (int i = 1; i <= ultimo; i++) {
            cadena = cadena + "[" + heap[i] + "]";
        }
        return cadena;
    }
}