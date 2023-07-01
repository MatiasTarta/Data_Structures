package tests;

import lineales.dinamicas.*;

public class testLineales {
    public static void main(String[] arg) {

        /*
         * Cola q = new Cola();
         * q.poner('a');
         * q.poner('b');
         * q.poner('c');
         * q.poner('d');
         * q.poner('e');
         * q.poner('f');
         * 
         * q.poner('#');
         * q.poner('a');
         * q.poner('b');
         * q.poner('c');
         * q.poner('d');
         * q.poner('#');
         * q.poner('q');
         * q.poner('w');
         * 
         * q.poner('r');
         * q.poner('t');
         * q.poner('y');
         * q.poner('#');
         * q.poner('s');
         * q.poner('j');
         * q.poner('#');
         * 
         * Lista resultado = new Lista();
         * resultado = invertirConVocales(q);
         * System.out.println("Cola antes de ser modificada" + q.toString());
         * System.out.println(resultado.toString());
         */

        Cola q1 = new Cola();
        q1.poner('A');
        q1.poner('B');
        q1.poner('C');
        q1.poner('B');
        q1.poner('A');
        q1.poner('$');
        q1.poner('C');
        q1.poner('D');
        q1.poner('D');
        q1.poner('E');
        q1.poner('$');
        q1.poner('A');
        q1.poner('F');
        q1.poner('C');
        q1.poner('C');
        q1.poner('F');
        q1.poner('A');
        q1.poner('$');

        System.out.println("Cantidad de Subcadenas Capicuas es: " + cuentaSecuencias(q1));

    }

    public static Lista invertirConVocales(Cola q) {
        Lista ls = new Lista();
        Cola colaClon = q.clone();
        Cola normal = new Cola();
        Pila inversa = new Pila();
        boolean vocal = false;
        Object elem;
        int i = 1;
        while (!(colaClon.esVacia())) {
            elem = (char) colaClon.obtenerFrente();
            if ((elem.equals('#'))) {
                if (vocal) {
                    while (!inversa.esVacia()) {
                        ls.insertar(inversa.obtenerTope(), i);
                        inversa.desapilar();
                        i++;
                    }
                    ls.insertar('#', i);
                    i++;
                    vocal = false;
                } else {
                    while (!normal.esVacia()) {
                        ls.insertar(normal.obtenerFrente(), i);
                        normal.sacar();
                        i++;
                    }
                    ls.insertar('#', i);
                    i++;
                }
                normal.vaciar();
                inversa.vaciar();
            } else {
                inversa.apilar(elem);
                normal.poner(elem);
                if (esVocal(elem)) {
                    vocal = true;
                }
            }
            colaClon.sacar();
        }
        ls.eliminar(ls.longitud());
        return ls;
    }

    public static boolean esVocal(Object caracter) {
        return caracter.equals('a') || caracter.equals('e') || caracter.equals('i') || caracter.equals('o')
                || caracter.equals('u');
    }

    public static int cuentaSecuencias(Cola q) {
        int res = 0;
        Cola colaClon = q.clone();

        Pila inversa = new Pila();
        Cola normal = new Cola();
        boolean esCapicua = false;
        Object elem;

        System.out.println(colaClon.toString());
        while (!colaClon.esVacia()) {
            elem = colaClon.obtenerFrente();
            if (elem.equals('$')) {
                while (!(inversa.esVacia() && normal.esVacia())) {
                    esCapicua = (inversa.obtenerTope().equals(normal.obtenerFrente()));
                    inversa.desapilar();
                    normal.sacar();
                }
                if (esCapicua) {
                    res++;
                }
                inversa.vaciar();
                normal.vaciar();
            } else {
                inversa.apilar(colaClon.obtenerFrente());
                normal.poner(colaClon.obtenerFrente());
            }
            colaClon.sacar();
        }

        return res;
    }
}
