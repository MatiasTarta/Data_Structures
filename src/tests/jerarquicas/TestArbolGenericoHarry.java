package tests.jerarquicas;

import jerarquicas.*;
import lineales.dinamicas.Lista;

public class TestArbolGenericoHarry {
    public static void main(String[] arg) {
        ArbolGen arbol1 = new ArbolGen();
        
         
          arbol1.insertar(10,null);
          arbol1.insertar(25,10);
          arbol1.insertar(33,10);
          arbol1.insertar(54,10);
          arbol1.insertar(15,25);
          arbol1.insertar(47,33);
          arbol1.insertar(18,33);
          arbol1.insertar(63,33);
          arbol1.insertar(9,18);
         
        
        
        System.out.println(arbol1.toString());
        Lista l= new Lista();

        l.insertar(15, 1);
        l.insertar(47, 2);
        l.insertar(9, 3);
        l.insertar(63, 4);
        l.insertar(54, 5);
        l.insertar(19, 6);
        System.out.println(l.toString());
        System.out.println("Son frontera debe retornar true: "+ arbol1.sonFrontera(l));
         
        

    }
}
