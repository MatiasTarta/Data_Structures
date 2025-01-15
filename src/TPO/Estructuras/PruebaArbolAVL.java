package TPO.Estructuras;

public class PruebaArbolAVL {
    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();

        // Inserción de nodos
        System.out.println("Insertando nodos en el árbol AVL...");
        arbol.insertar(30, "Dato30");
        arbol.insertar(20, "Dato20");
        arbol.insertar(40, "Dato40");
    
      

        // Caso adicional: probar rotaciones
        System.out.println("\nInsertando nodo que causa rotación...");
        arbol.insertar(10, "Dato");
        arbol.insertar(5, "Dato");
        arbol.insertar(7, "Dato");
        arbol.insertar(9, "Dato");
        arbol.insertar(2, "Dato2");  // Esto debería desencadenar una rotación en el árbol
        arbol.insertar(1, "Dato1");

        arbol.balancear(arbol.getRaiz(),null);

        System.out.println("Balance del Arbol: "+arbol.getBalance(arbol.getRaiz()));
        System.out.println(arbol.toString());
    }
}
