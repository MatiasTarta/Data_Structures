package tests.conjuntistas;
import conjuntistas.*;
import lineales.dinamicas.*;
public class TestArbolABB {
    public static void main(String[] args){
        ArbolABB a= new ArbolABB();


        a.insertar(16);
        a.insertar(6);
        a.insertar(20);
        a.insertar(2);
        a.insertar(12);
        a.insertar(8);
        a.insertar(14);
        a.insertar(28);
        a.insertar(26);
        a.insertar(13);
        /*
         * Lista l= a.listar();
        System.out.println(l.toString());
         */
        System.out.println(a.toString());
        boolean prueba = a.eliminarelemanterior(16);
        System.out.println(a.toString());
        
    }
}
