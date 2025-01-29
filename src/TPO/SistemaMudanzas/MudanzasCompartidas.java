package TPO.SistemaMudanzas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import TPO.Estructuras.Diccionario;
import TPO.Estructuras.GestorSolicitudes;
import TPO.Estructuras.GrafoEtiquetado;
import TPO.Estructuras.MapeoAMuchos;
import lineales.dinamicas.Lista;

public class MudanzasCompartidas {
    private static Diccionario diccionario = new Diccionario();
    private static GrafoEtiquetado mapaRutas = new GrafoEtiquetado();
    private static MapeoAMuchos infoClientes= new MapeoAMuchos(12);
    private static GestorSolicitudes solicitudes=new GestorSolicitudes();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int entrada = 1;
        
        String archivo = Paths.get("src", "TPO", "SistemaMudanzas", "CargaInicial.txt").toString();

        hacerCargaInicial(archivo);
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
                    hacerCargaInicial(archivo);
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
                System.out.println("Dada una clave de un Cliente muestra toda la informacion sobre el");
                System.out.println("Ingrese el tipo de Documento del cliente:");
                String tipo = sc.next();
                sc.nextLine();
                System.out.println("Ingrese el numero de Documento del cliente");
                int numero = sc.nextInt();
                System.out.println((infoClientes.obtenerValor(tipo, numero)).toString());
                    break;
                case 2:
                    menuConsultasCiudades(sc, 1);
                    break;
                case 3:
                    // consultas sobre mapa
                    menuConsultasMapa(sc, 1);
                    break;
                case 4:
                    // verificar viaje
                    break;
                default:
                    break;
            }
        }
    }


    public static void menuConsultasCiudades(Scanner sc, int entrada){
        while (entrada != 0) {
            System.out.println("1.Dado un código postal de una ciudad, mostrar toda su información");
            System.out.println("2.Dado un prefijo, devolver todas las ciudades cuyo código postal comienza con dicho prefijo");
            System.out.println("Ingrese 0 para cerrar");
            entrada = sc.nextInt();
            System.out.println("\n");
            switch (entrada) {
                case 1:
                    System.out.println("Ingrese el codigo Postal");
                    int codigo = sc.nextInt();
                    System.out.println(mostrarCiudadesPrefijo(codigo));
                    break;
                case 2:
                    System.out.println("Ingrese el prefijo");
                    int prefijo = sc.nextInt();
                    System.out.println(mostrarCiudadesPrefijo(prefijo).toString());
                    break;
                default:
                    break;
            }
        }
    }

    public static void menuConsultasMapa(Scanner sc, int entrada){
        while (entrada != 0){
            System.out.println("1. Obtener el camino mas directo");//mas directo= pasa por menos ciudades
            System.out.println("2. Obtener el camino mas corto en distancia");
            System.out.println("3. Obtener todos los caminos posibles entre dos ciudades pasando por una ciudad Intermedia");
            System.out.println("4. Verificar si es posible viajar entre 2 ciudades con una cantidad maxima de Kilometros");
            System.out.println("Ingrese 0 para cerrar");
            entrada= sc.nextInt();
            switch (entrada) {
                case 1:
                    System.out.println("Ingrese Ciudad de Origen");
                    int origen=sc.nextInt();
                    System.out.println("Ingrese ciudad de Destino");
                    int destino=sc.nextInt();
                    System.out.println("El camino mas directo desde "+origen+" hasta "+destino+" es:");
                    System.out.println((mapaRutas.caminoMasCorto(origen, destino)).toString());
                    break;
                case 2:
                    System.out.println("Ingrese Ciudad de Origen");
                    int origenA=sc.nextInt();
                    System.out.println("Ingrese ciudad de Destino");
                    int destinoA=sc.nextInt();
                    System.out.println("El camino mas corto desde "+origenA+" hasta "+destinoA+" es:");
                    System.out.println((mapaRutas.caminoMenorDistancia(origenA, destinoA)).toString());
                    break;
                case 3:
                    //hay que arreglar el modulo
                    break;
                case 4:
                System.out.println("Ingrese Ciudad de Origen");
                int origenC=sc.nextInt();
                System.out.println("Ingrese ciudad de Destino");
                int destinoC=sc.nextInt();
                System.out.println("Ingrese el maximo de kilometros");
                int km=sc.nextInt();
                Lista nueva=mapaRutas.caminoMayorDistancia(origenC, destinoC, km);
                if ((nueva).esVacia()) {
                    System.out.println("No es posible viajar entre esas 2 ciudades recorriendo como maximo "+km+" kilometros");
                }else{
                    System.out.println("Es posible viajar entre las dos ciudades Siguiendo el camino:");
                    System.out.println(nueva.toString());
                }
                    break;
                default:
                    break;
            }
        }
    }

    public static void hacerCargaInicial(String archivo) {
        int i = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                switch (partes[0]) {
                    case "C":
                        cargarCiudad(Integer.parseInt(partes[1]), partes[2], partes[3]);
                        break;
                    case "R":
                        insertarRuta(Integer.parseInt(partes[1]), Integer.parseInt(partes[2]),
                                Double.parseDouble(partes[3]), mapaRutas);
                        break;
                    case"P":
                        infoClientes.asociar(partes[1], Integer.parseInt(partes[2]), partes[3], partes[4], Integer.parseInt(partes[5]),partes[6]);
                        break;
                    case"S":
                        solicitudes.cargarSolicitud(Integer.parseInt(partes[1]), Integer.parseInt(partes[2]), partes[3],partes[4], Integer.parseInt(partes[5]), Integer.parseInt(partes[6]), Double.parseDouble(partes[7]), partes[8], partes[9], Boolean.parseBoolean(partes[10]));
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

    public static void cargarCiudad(int codigo, String nombre, String provincia) {
        Ciudad ciudad = new Ciudad(codigo, nombre, provincia);
        boolean exito = diccionario.insertar(codigo, ciudad);
        if (exito) {

        }
    }

    public static String mostrarInfoCiudad(Comparable codigoPostal) {
        // Dado un código postal de una ciudad, mostrar toda su información
        return (diccionario.obtenerDato(codigoPostal).toString());
    }

    public static Lista mostrarCiudadesPrefijo(int prefijo) {
        return diccionario.ciudadesPrefijo(prefijo);
    }


    public Lista solicitudesPosibles(int codigoO,int codigoD){
        Lista nueva= new Lista();
        if (solicitudes.verificarEspacioDisponible(codigoO, codigoD, 4)) {
            nueva=(mapaRutas.ciudadesIntermedias(8300, 1900));
        }else{
            //si devuelve vacio no hay espacio disponible en el camion
        }
        return nueva;
    }

}
