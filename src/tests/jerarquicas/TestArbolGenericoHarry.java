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
            System.out.println(arbol1.padre('X'));
           /*
            *  Lista lista1= arbol1.listarInorden();
            System.out.println("Lista Inorden: "+ lista1.toString() );
            Lista l2=arbol1.listarPreorden();
            System.out.println("Lista Preorden(Devuelve A|B|E|Z|W|X|Y|F|G|C|D|H): "+l2.toString());
            Lista l3=arbol1.listarPosorden();
            System.out.println("Lista PosOrden: "+l3.toString());

            */


            
        
    }
}
