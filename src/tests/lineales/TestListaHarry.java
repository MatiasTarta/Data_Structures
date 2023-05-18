package tests.lineales;
import java.util.List;

import javax.swing.SpinnerDateModel;
import lineales.dinamicas.Pila;
import lineales.dinamicas.Lista;
public class TestListaHarry {
    public static void main(String[] arg){
        
        /*
         * Lista lista1 = new Lista();
        lista1.insertar(1, 1);
        for(int i=2;i<10;i++){
            lista1.insertar(3, i);
        }
        
        System.out.println(lista1.toString());
        lista1.eliminarApariciones(3);
        System.out.println("Lista nueva"+lista1.toString());
        Lista lista2= lista1.obtenerMultiplos(2);
         */
        
        Pila pila1=new Pila();
        for(int i=1;i<=5;i++){
            //pila1.apilar(i);
        }
        Pila pila2=new Pila();
        for(int j=1;j<=5;j++){
           // pila2.apilar(j);
        }
        System.out.println(pila1.toString());
        System.out.println(pila2.toString());
        System.out.println(pila1.equals(pila2));

    }
}
