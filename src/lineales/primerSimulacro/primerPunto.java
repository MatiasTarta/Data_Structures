package lineales.primerSimulacro;

import lineales.dinamicas.*;

public class primerPunto {
    public static void main(String[] args) {
        Lista l = new Lista();
        l.insertar('A', 1);
        l.insertar('B', 2);
        l.insertar('C', 3);
        l.insertar('D', 4);
        l.insertar('E', 5);
        l.insertar('F', 6);
        l.insertar('G', 7);
        l.insertar('H', 8);
        l.insertar('I', 9);
        l.insertar('J', 10);

        System.out.println("Lista: " + l.toString());
        Lista nueva = l.obtenerMultiplos(3);
        System.out.println("Lista modificada para n=3 :" + nueva.toString());
    }

}
