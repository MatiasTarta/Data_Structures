package tests.conjuntistas;
import conjuntistas.*;
public class TestHeap {
    public static void main(String[] args) {
        // Crear un heap mínimo
        HeapMin heap = new HeapMin();

        // Insertar elementos en el heap
        heap.insertar(5);
        heap.insertar(3);
        heap.insertar(8);
        heap.insertar(1);
        heap.insertar(10);

        /*
         * // Imprimir el heap antes de la eliminación
        System.out.println("Heap antes de la eliminación:");
        heap.toString();

        // Eliminar el elemento mínimo del heap
         heap.eliminarCima();
        System.out.println("Elemento mínimo eliminado: ");

        // Imprimir el heap después de la eliminación
        System.out.println("Heap después de la eliminación:");
         */
        heap.toString();
    }
}
