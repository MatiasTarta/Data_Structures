package tests.jerarquicas;

import jerarquicas.*;
import lineales.dinamicas.Lista;

public class TestArbolGenericoHarry {
    public static void main(String[] arg) {
        ArbolGen arbol1 = new ArbolGen();
        

          arbol1.insertar('A',null);
          arbol1.insertar('B','A');
          arbol1.insertar('C','A');
          arbol1.insertar('D','A');
          arbol1.insertar('E','B');
          arbol1.insertar('F','B');
          arbol1.insertar('G','B');
          arbol1.insertar('H','D');
          arbol1.insertar('I','G');
          arbol1.insertar('J','I');
          arbol1.insertar('K','E');
          arbol1.insertar('L','E');
          arbol1.insertar('M','L');
        System.out.println(arbol1.toString());
        Lista l= arbol1.listarPorNiveles(2);
        System.out.println(l.toString());

        

    }
}
