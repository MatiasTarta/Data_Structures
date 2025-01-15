package TPO.Estructuras;

public class PruebaArbolAVL {
    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();

        // Inserción de nodos
        System.out.println("Insertando nodos en el árbol AVL...");
        arbol.insertar(30, "Dato30");
        arbol.insertar(10, "Dato10");
        arbol.insertar(6, "Dato6");
         arbol.insertar(7, "Dato7");
        arbol.insertar(9, "Dato9");
        System.out.println(arbol.toString());
    }
}
