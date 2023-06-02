package tests.conjuntistas;
import conjuntistas.*;
import lineales.dinamicas.*;
public class TestArbolABB {
    public static void main(String[] args){
        ArbolABB a= new ArbolABB();


        a.insertar(1);
        a.insertar(2);
        a.insertar(3);
        a.insertar(9);
        a.insertar(4);
        Lista l= a.listar();
        System.out.println(l.toString());
        
    }
}
