package Parcial;
import lineales.dinamicas.*;
public class punto2{

    public static void main(String[] arg){
        	Lista ls= new Lista();
            for(int i=1;i<=10;i++){
                ls.insertar(i, i);
            }
            System.out.println(ls.toString());
            System.out.println("Intercambiamos el 1 por el 5");
            ls.cambiarPosicion(5, 1);
            System.out.println("Lista Nueva: "+ls.toString());
        }
}