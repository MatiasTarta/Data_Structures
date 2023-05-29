import java.beans.PropertyChangeListenerProxy;

public class HeapMin {
    private int tamanio = 10;
    private Comparable[] heap;
    private int ultimo;
    //en un Heap la posicion del Padre es K, la del HI es 2k y la del HD es 2k+1
    public HeapMin(){
        this.heap= Comparable[] heap;
        this.ultimo=0;
    }

    public boolean insertar(int num) {
        boolean puesto;
        boolean esVacio = this.esVacio();
        puesto = poner(num);
        if (!esVacio()) {
            ordenarUltimo();
        }
        return puesto;
    }

    boolean poner(int num) {
        if (esLLeno()) {
            aumentarArreglo();
        }
        // si el heap ya esta lleno aumenta su tamanio
        heap[ultimo + 1] = num;
        // coloca el numero en el ultimo lugar
        ultimo = ultimo + 1;
        // corre el ultimo lugar
        return true;
    }

    private void aumentarArreglo() {
        this.tamanio = tamanio + 10;
        Comparable[] temp = new Comparable[tamanio];
        // crea un arreglo auxiliar mas grande q el anterior
        int longi = heap.length;
        for (int i = 0; i < longi; i++) {
            temp[i] = heap[i];
            // clona el arreglo anterior
        }
        heap = temp;
        // el nuevo arreglo es mas grande que el anterior pero mas largo;

    }

    private void ordenarUltimo() {
        int posicion = ultimo; // Guarda la posición del último elemento en el hep

        while ((heap[posicion].compareTo(heap[posicion / 2])) < 0) {
            Comparable padre = heap[posicion / 2]; // Guarda el elemento padre
            heap[posicion / 2] = heap[posicion]; // Reemplaza el padre con el elemento actual
            heap[posicion] = padre; // Reemplaza el elemento actual con el padre
            posicion = posicion / 2; // Mueve la posición al padre en el siguiente nivel
        }
    }

    public boolean eliminarCima() {
        boolean exito;
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

    private void hacerBajar(int posPadre){
        int posH;
        Comparable temp= this.heap[posPadre];
        boolean salir=false;
        while(!salir){
            posH=posPadre*2;
            //se calcula posicion del hijo izquierdo
            if(posH<=this.ultimo){
                //temp tiene al menos un hijo izq y lo considera menor
                if(posH<this.ultimo){
                    //hijo menor tiene hermano derecho
                    if((this.heap[posH+1].compareTo(this.heap[posH]))<0){
                        //el hijo derecho es el menor de los dos
                        posH++;
                    }
                }
                //compara al hijo menor con el padre
                if((this.heap[posH].compareTo(temp))<0){
                    //el hijo es menjor que el padre, los intercambia
                    this.heap[posPadre]=this.heap[posH];
                    this.heap[posH]=temp;
                    posPadre=posH;
                }else{
                    //el padre es menor q sus hijos, esta bien ubicado
                    salir=true;
                }
            }else{
                //el temp es hoja, esta bien ubicado
                salir=true;
            }
        }
    }

    public boolean esVacio() {
        return ultimo == 0;
    }

    public boolean esLLeno() {
        return ultimo == heap.length - 1;
    }
    public String toString() {
        String salida = "[";
        for (int i = 0; i < tamanio; i++) {
            salida = salida + this.heap[i];
            if (i < tamanio - 1) {
                salida = salida + ", ";
            }
        }
        salida = salida + "]";
        return salida;
    }
}