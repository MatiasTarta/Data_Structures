package TPO.Estructuras;

public class PruebaArbolAVL {
    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();

        // Inserción de nodos
        System.out.println("Insertando nodos en el árbol AVL...");
        arbol.insertar(30, "Dato30");
       
        arbol.insertar(10, "Dato");
        arbol.insertar(5, "Dato");
        arbol.insertar(7, "Dato");
        arbol.insertar(9, "Dato");
      

        arbol.balancear(arbol.getRaiz(),null);

        System.out.println(arbol.toString());
    }
}
