package TPO.SistemaMudanzas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import TPO.Estructuras.ArbolAVL;
import TPO.Estructuras.GrafoEtiquetado;
import lineales.dinamicas.Lista;

public class MudanzasCompartidas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int entrada = 1;
        GrafoEtiquetado mapaRutas = new GrafoEtiquetado();
        ArbolAVL diccionario = new ArbolAVL();
        String archivo = Paths.get("src", "TPO", "SistemaMudanzas", "CargaInicial.txt").toString();

        System.out.println("Bienvenido al sistema de Mudanzas de la Empresa");
        System.out.println("Que desea Hacer a Continuacion?");
        while (entrada != 0) {
            System.out.println("<------------------Menu Principal------------------>");
            System.out.println("Ingrese 1 para realizar una carga Inicial de un lote fijo");
            System.out.println("Ingrese 2 para tratar Altas,Bajas y Modificaiones del sistema");
            System.out.println("Ingrese 3 para realizar consultas");
            System.out.println("Ingrese 4 para mostrar las estructuras del sistema");
            System.out.println("Ingrese 0 para cerrar");
            System.out.println("<-------------------------------------------------->");
            entrada = sc.nextInt();
            System.out.println("\n");
            switch (entrada) {
                case 1:
                    // carga inicial
                    hacerCargaInicial(archivo, mapaRutas, diccionario);
                    break;
                case 2:
                    // abm
                    break;
                case 3:
                    // consultas
                    menuConsultas(sc, 1);
                    break;
                case 4:
                    // muestra estructuras del sistema
                    break;
                default:
                    break;
            }
        }

    }

    public static void menuConsultas(Scanner sc, int entrada) {
        System.out.println("Bienvenido al menu de Consultas");
        System.out.println("Que desea Hacer a Continuacion?");

        while (entrada != 0) {
            System.out.println("<------------------Menu Consultas------------------>");
            System.out.println("Ingrese 1 para realizar consultas sobre los clientes");
            System.out.println("Ingrese 2 para realizar consultas sobre las ciudades");
            System.out.println("Ingrese 3 para realizar consultas sobre los viajes");
            System.out.println("Ingrese 4 para verificar un viaje");
            System.out.println("Ingrese 0 para cerrar");
            System.out.println("<-------------------------------------------------->");
            entrada = sc.nextInt();

            System.out.println("\n");
            switch (entrada) {
                case 1:
                    // consultas sobre clientes
                    break;
                case 2:
                    // consultas sobre ciudades
                    break;
                case 3:
                    // consultas sobre viajes
                    break;
                case 4:
                    // verificar viaje
                    break;
                default:
                    break;
            }
        }
    }

    public static void hacerCargaInicial(String archivo, GrafoEtiquetado mapa, ArbolAVL diccionario) {
        int i = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                switch (partes[0]) {
                    case "C":
                        cargarCiudad(Integer.parseInt(partes[1]), partes[2], partes[3], diccionario);
                        break;
                    case "R":
                        insertarRuta(Integer.parseInt(partes[1]), Integer.parseInt(partes[2]),
                                Double.parseDouble(partes[3]), mapa);
                        break;
                    default:
                        System.out.println("Formato desconocido en la línea: " + linea + " " + i);
                        break;
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Carga inicial realizada");
    }

    public static void insertarRuta(int codigoOrigen, int codigoDestino, double distancia, GrafoEtiquetado mapa) {
        if (!mapa.existeVertice(codigoOrigen)) {
            mapa.insertarVertice(codigoOrigen);
        }

        if (!mapa.existeVertice(codigoDestino)) {
            mapa.insertarVertice(codigoDestino);
        }

        // Solo agregar el arco si ambos nodos han sido insertados
        if (mapa.existeVertice(codigoOrigen) && mapa.existeVertice(codigoDestino)) {
            mapa.insertarArco(codigoOrigen, codigoDestino, distancia);
        }
    }

    public static void cargarCiudad(int codigo, String nombre, String provincia, ArbolAVL diccionario) {
        Ciudad ciudad = new Ciudad(codigo, nombre, provincia);
        boolean exito = diccionario.insertar(codigo, ciudad);
        if (exito) {

        }
    }

    public static String mostrarInfoCiudad(Comparable codigoPostal, ArbolAVL diccionario) {
        // Dado un código postal de una ciudad, mostrar toda su información
        return (diccionario.obtenerDato(codigoPostal).toString());
    }

    public static Lista mostrarCiudadesPrefijo(int prefijo, ArbolAVL diccionario) {
        return diccionario.ciudadesPrefijo(prefijo);
    }

}
