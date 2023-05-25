package Parcial;
import jerarquicas.*;
public class punto3 {
    public static void main(String[] arg){
        ArbolBin arbol1= new ArbolBin();
        arbol1.insertar(1, null, 'I');
        arbol1.insertar(2, 1, 'I');
        arbol1.insertar(5, 2, 'I');
        arbol1.insertar(4, 1, 'D');
        arbol1.insertar(2, 2, 'D');
        arbol1.insertar(2, 4, 'I');
        arbol1.insertar(7, 4, 'D');
        arbol1.insertar(8, 7, 'I');
        

        System.out.println(arbol1.toString());

        System.out.println("");
        System.out.println("Debe devolver false: "+arbol1.menorCantApariciones(2, 2));

    }
}
