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
        respuestaEsperada();
        System.out.println("Respuesta obtenida");
        System.out.println(nuevo.toString());
    }

    public static void respuestaEsperada(){
        System.out.println("RESPUESTA ESPERADA");
        System.out.println("└── 20");
        System.out.println("    ├── I-10");
        System.out.println("    │   ├── I-5");
        System.out.println("    │   └── D-15");
        System.out.println("    └── D-40");
        System.out.println("        ├── I-30");
        System.out.println("        │   ├── I-25");
        System.out.println("        │   └── D-35");
        System.out.println("        └── D-50");
        System.out.println("            ├── I-45");
        System.out.println("            └── D-60");
        
    }
}
