package Parcial;
import lineales.dinamicas.*;
public class punto1{
    public static void main(String[] arg){
        
        Cola q= new Cola();
        q.poner('a');
        q.poner('b');
        q.poner('c');
        q.poner('d');
        q.poner('e');
        q.poner('f');
        q.poner('#');
        q.poner('a');
        q.poner('b');
        q.poner('c');
        q.poner('d');
        q.poner('#');
        q.poner('q');
        q.poner('w');
        
        q.poner('r');
        q.poner('t');
        q.poner('y');
        q.poner('#');
        q.poner('s');
        q.poner('j');
        
        Lista l= new Lista();
        l=invertirConVocales(q);
        
         System.out.println(l.toString());


        
    }

    public static  Lista invertirConVocales(Cola q){
        Lista ls= new Lista();
        int i= 1;
        while(!q.esVacia()){
            Pila p= new Pila();
            while(!q.esVacia() && !(q.obtenerFrente().equals('#'))){
                p.apilar(q.obtenerFrente());
                q.sacar();
            }
            p= acomodar(p);

            while(!p.esVacia()){
                ls.insertar(p.obtenerTope(), i);
                p.desapilar();
                i++;
            }
            ls.insertar('#', i);
            i++;
            q.sacar();
        }
        
        return ls;
    }
    public static Pila acomodar (Pila p){
        Pila p2= p.clone();
        boolean vocal=false;
        while(!(p2.esVacia()) && !vocal){
            if(esVocal(p2.obtenerTope())){
                vocal=true;
            }
            p2.desapilar();
        }
        if(vocal==true){
            return p;
        }else{
            return invertir(p);
        }
    }
    
        public static Pila invertir(Pila p) {
            Pila pilaAuxiliar = new Pila();
            while (!p.esVacia()) {
                pilaAuxiliar.apilar(p.obtenerTope());
                p.desapilar();
            }
        return pilaAuxiliar;
        }

    public static boolean esVocal(Object caracter) {
        return caracter.equals('a') || caracter.equals('e') || caracter.equals('i') || caracter.equals('o') || caracter.equals('u');
    }
}