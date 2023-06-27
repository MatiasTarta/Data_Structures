package lineales.primerSimulacro;

import lineales.dinamicas.*;

public class segundoPunto {
    public static void main(String[] args) {
        /*
         * Cola q = new Cola();
         * 
         * q.poner('A');
         * q.poner('B');
         * q.poner('#');
         * q.poner('C');
         * q.poner('#');
         * q.poner('D');
         * q.poner('E');
         * q.poner('F');
         * 
         * System.out.println("Cola Original: " + q.toString());
         * Cola generada = new Cola();
         * generada = generar(q);
         * System.out.println("Cola generada: " + generada.toString());
         */
        Cola q = new Cola();

        q.poner('{');
        q.poner('5');
        q.poner('+');
        q.poner('[');
        q.poner('8');
        q.poner('*');
        q.poner('9');
        q.poner('-');
        q.poner('(');
        q.poner('4');
        q.poner('/');
        q.poner('2');
        q.poner(')');
        q.poner('+');
        q.poner('7');
        q.poner(']');
        q.poner('-');
        q.poner('1');
        q.poner('}');
        System.out.println(q.toString());

    }

    public static Cola generar(Cola c1) {
        Cola c2 = new Cola();

        while (c1.obtenerFrente() != null) {
            Cola colaAux = new Cola();
            Pila p = new Pila();
            while (c1.obtenerFrente() != null && !(c1.obtenerFrente().equals('#'))) {
                c2.poner(c1.obtenerFrente());
                colaAux.poner(c1.obtenerFrente());
                p.apilar(c1.obtenerFrente());
                c1.sacar();
            }
            while (p.obtenerTope() != null) {
                c2.poner(p.obtenerTope());
                p.desapilar();
            }
            while (colaAux.obtenerFrente() != null) {
                c2.poner(colaAux.obtenerFrente());
                colaAux.sacar();
            }
            c2.poner('#');
            c1.sacar();

        }
        return c2;
    }

}
