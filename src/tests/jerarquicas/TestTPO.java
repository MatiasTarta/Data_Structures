package tests.jerarquicas;

import jerarquicas.*;
import lineales.dinamicas.Lista;

public class TestTPO {
    static String sOk = "\u001B[32m OK! \u001B[0m", sErr = " \u001B[31m ERROR \u001B[0m";

    public static void main(String[] args) {
        ArbolGen arbol = arbolPrueba();
        Lista l1, l2, l3, l4, l5;
        l1 = listaPrueba1();
        l2 = listaPrueba2();
        l3 = listaPrueba3();
        l4 = listaPrueba4();
        l5 = new Lista();
        System.out.println("\n\n");
        System.out.println(
                "-------------------------------------------------------------------------------------------------------"
                        + "---------------------------------------------------------------------------");
        System.out.println("********************************");
        System.out.println("*      Test sonFrontera        *");
        System.out.println("********************************");
        System.out.println("\n");
        System.out.println("Resultado sonFrontera con lista con todas las hojas. Tiene que dar " + sOk + " --> "
                + ((arbol.sonFrontera(l1)) ? sOk : sErr));
        System.out.println("lista l1: " + l1.toString());
        System.out.println("\n");

        System.out.println("Resultado sonFrontera con lista con elementos repetidos. Tiene que dar " + sOk + " --> "
                + ((arbol.sonFrontera(l2)) ? sOk : sErr));
        System.out.println("lista l2: " + l2.toString());
        System.out.println("\n");
        System.out.println("Resultado sonFrontera con lista con elementos que no son hojas. Tiene que dar " + sErr
                + " --> " + ((arbol.sonFrontera(l3)) ? sOk : sErr));
        System.out.println("lista l3: " + l3.toString());
        System.out.println("\n");
        System.out.println("Resultado sonFrontera lista con elementos inexistentes en el arbol generico.Tiene que dar "
                + sErr + " --> " + ((arbol.sonFrontera(l4)) ? sOk : sErr));
        System.out.println("lista l4: " + l4.toString());
        System.out.println("\n");

        System.out.println("Resultado sonFrontera con lista vacia.Tiene que dar " + sErr + " --> "
                + ((arbol.sonFrontera(l5)) ? sOk : sErr));
        System.out.println("lista l5: " + l5.toString());
        System.out.println("\n");
        arbol.vaciar();
        System.out.println(
                "Resultado con Arbol vacio.Tiene que dar " + sErr + " --> " + ((arbol.sonFrontera(l1)) ? sOk : sErr));
        System.out.println("lista l1: " + l1.toString());
        System.out.println("\n");
        System.out.println(
                "-------------------------------------------------------------------------------------------------------"
                        + "---------------------------------------------------------------------------");
        ArbolGen arbol1 = arbol;
        ArbolGen arbol2 = arbol1.clone();
        ArbolGen arbol3 = arbol1.clone();
        arbol3.insertar('X', 'K');
        arbol3.insertar('Y', 'X');
        ArbolGen arbol4 = arbolPrueba4();
        ArbolGen arbol5 = arbol1.clone();
        ArbolGen arbol6 = new ArbolGen();
        ArbolGen arbol7 = arbolPrueba7();

        System.out.println("********************************");
        System.out.println("*      Test equals             *");
        System.out.println("********************************");
        System.out.println();

        // Comparación entre arboles iguales
        System.out.println("Resultado equals (arboles iguales). Tiene que dar " + sOk + " --> "
                + ((arbol1.equals(arbol2)) ? sOk : sErr));

        // Comparación entre arboles de diferente longitud
        System.out.println("Resultado equals (arboles de diferente longitud). Tiene que dar " + sErr + " --> "
                + ((arbol1.equals(arbol3)) ? sOk : sErr));

        // Comparación entre arboles con diferente estructura pero mismo contenido
        System.out.println("Resultado equals (arboles con diferente estructura pero mismo contenido). Tiene que dar "
                + sErr + " --> " + ((arbol1.equals(arbol4)) ? sOk : sErr));

        arbol5.vaciar();
        // Comparación entre arboles vacíos
        System.out.println("Resultado equals (arboles vacíos). Tiene que dar " + sOk + " --> "
                + ((arbol5.equals(arbol6)) ? sOk : sErr));

        // Comparación entre arboles con mismo tamaño pero diferentes valores
        System.out.println("Resultado equals (arboles con mismo tamaño pero diferentes valores). Tiene que dar " + sErr
                + " --> " + ((arbol1.equals(arbol7)) ? sOk : sErr));

    }

    public static ArbolGen arbolPrueba() {
        ArbolGen a = new ArbolGen();
        System.out.println("********************************");
        System.out.println("*      Insercion basica        *");
        System.out.println("********************************");
        System.out.println("Inserto el A en raiz " + ((a.insertar('A', null)) ? sOk : sErr));
        System.out.println("Inserto B como hijo de A " + ((a.insertar('B', 'A')) ? sOk : sErr));
        System.out.println("Inserto C como hijo de A " + ((a.insertar('C', 'A')) ? sOk : sErr));
        System.out.println("Inserto D como hijo de A " + ((a.insertar('D', 'A')) ? sOk : sErr));
        System.out.println("Inserto E como hijo de B " + ((a.insertar('E', 'B')) ? sOk : sErr));
        System.out.println("Inserto F como hijo de B " + ((a.insertar('F', 'B')) ? sOk : sErr));
        System.out.println("Inserto H como hijo de D " + ((a.insertar('H', 'D')) ? sOk : sErr));
        System.out.println("Inserto D como hijo de D " + ((a.insertar('I', 'D')) ? sOk : sErr));
        System.out.println("Inserto J como hijo de D " + ((a.insertar('J', 'D')) ? sOk : sErr));
        System.out.println("Inserto K como hijo de E " + ((a.insertar('K', 'E')) ? sOk : sErr));
        System.out.println("Inserto L como hijo de E " + ((a.insertar('L', 'E')) ? sOk : sErr));
        System.out.println("\n toString()  deberia dar: \n"
                + "\n                                A"
                + "\n                +---------------+------------+"
                + "\n                |               |            |"
                + "\n                B               C            D"
                + "\n            +---+---+                 +------+-------+"
                + "\n            |       |                 |      |       |"
                + "\n            E       F                 H      I       J"
                + "\n        +---+----+"
                + "\n        |        |"
                + "\n        K        L"
                + "\n" + a.toString());
        System.out.println("\n");
        return a;
    }

    public static Lista listaPrueba1() {
        Lista l1 = new Lista();
        l1.insertar('C', 1);
        l1.insertar('F', 2);
        l1.insertar('K', 3);
        l1.insertar('L', 4);
        l1.insertar('H', 5);
        l1.insertar('I', 6);
        l1.insertar('J', 7);
        return l1;
    }

    public static Lista listaPrueba2() {
        Lista l2 = new Lista();
        l2.insertar('C', 1);
        l2.insertar('F', 2);
        l2.insertar('K', 3);
        l2.insertar('C', 4);
        l2.insertar('L', 5);
        l2.insertar('H', 6);
        l2.insertar('I', 7);
        l2.insertar('J', 8);
        return l2;
    }

    public static Lista listaPrueba3() {
        Lista l3 = new Lista();
        l3.insertar('C', 1);
        l3.insertar('F', 2);
        l3.insertar('K', 3);
        l3.insertar('L', 4);
        l3.insertar('D', 5);
        l3.insertar('J', 6);
        return l3;
    }

    public static Lista listaPrueba4() {
        Lista l4 = new Lista();
        l4.insertar('C', 1);
        l4.insertar('F', 2);
        l4.insertar('K', 3);
        l4.insertar('H', 4);
        l4.insertar('M', 5);
        return l4;
    }

    public static ArbolGen arbolPrueba4() {
        ArbolGen a = new ArbolGen();
        a.insertar('A', null);
        a.insertar('B', 'A');
        a.insertar('C', 'A');
        a.insertar('D', 'B');
        a.insertar('E', 'D');
        a.insertar('F', 'C');
        a.insertar('H', 'F');
        a.insertar('I', 'D');
        a.insertar('J', 'D');
        a.insertar('K', 'E');
        a.insertar('L', 'K');

        return a;
    }

    public static ArbolGen arbolPrueba7() {
        ArbolGen a = new ArbolGen();
        a.insertar('X', null);
        a.insertar('Z', 'X');
        a.insertar('Y', 'X');
        a.insertar('W', 'X');
        a.insertar('1', 'Z');
        a.insertar('E', 'Z');
        a.insertar('E', 'Z');
        a.insertar('F', 'W');
        a.insertar('7', 'W');
        a.insertar('9', 'W');
        a.insertar('A', '1');
        a.insertar('6', '1');

        return a;
    }
}