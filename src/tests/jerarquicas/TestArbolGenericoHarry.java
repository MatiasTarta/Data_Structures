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
        /*
         * System.out.println("Inserto B como hijo de A "+ ((a.insertar('B','A')) ? sOk : sErr) );
         */
         ArbolGen a2= arbol1.clone();
         //a2.insertar('W', 'A');
        Lista l1= arbol1.listarPorNiveles();
        Lista l2= a2.listarPorNiveles();
        System.out.println(l1.toString());
        System.out.println(l2.toString());
        
        System.out.println(arbol1.equals(a2));

    }
}
