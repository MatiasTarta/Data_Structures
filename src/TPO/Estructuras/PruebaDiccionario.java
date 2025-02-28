package TPO.Estructuras;

public class PruebaDiccionario {
    public static void main(String[] args) {
        Diccionario nuevo = new Diccionario();
        nuevo.insertar(5, "MARC");
        nuevo.insertar(10, "A");
        nuevo.insertar(15, "B");
        nuevo.insertar(20, "C");
        nuevo.insertar(40, "D");
        nuevo.insertar(30, "E");
        nuevo.insertar(50, "F");
        nuevo.insertar(25, "G");
        nuevo.insertar(35, "H");
        nuevo.insertar(45, "I");
        nuevo.insertar(60, "J");
        System.out.println(nuevo.toString());
    }
}
