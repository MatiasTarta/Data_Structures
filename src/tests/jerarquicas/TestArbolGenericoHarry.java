package tests.jerarquicas;

import jerarquicas.*;
import lineales.dinamicas.Lista;

public class TestArbolGenericoHarry {
    public static void main(String[] arg) {
        ArbolGen arbol1 = new ArbolGen();
       
            arbol1.insertar('A', null);
            arbol1.insertar('B', 'A');
            arbol1.insertar('C', 'A');
            arbol1.insertar('D', 'A');
            arbol1.insertar('E', 'B');
            arbol1.insertar('F', 'B');
            arbol1.insertar('G', 'B');
            arbol1.insertar('H', 'D');
            arbol1.insertar('Z', 'E');
            arbol1.insertar('Y', 'E');
            arbol1.insertar('W', 'Z');
            arbol1.insertar('X', 'Z');
            System.out.println(arbol1.toString());
            /*
             * Lista l= arbol1.ancestros('X');
            System.out.println(l.toString());
             */

            System.out.println(arbol1.pertenece('H'));
        
    }
}
