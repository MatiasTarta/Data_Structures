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

        System.out.println(heap.toString());
    }
}
