package TPO.Estructuras;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import TPO.SistemaMudanzas.Ciudad;
import lineales.dinamicas.Lista;

public class TestCarga {
    public static GrafoEtiquetado mapaRutas = new GrafoEtiquetado();
    public static Diccionario diccionario = new Diccionario();
    private static MapeoAMuchos infoClientes= new MapeoAMuchos(5);
    public static void main(String[] args) {
        String archivo = Paths.get("src", "TPO", "SistemaMudanzas", "CargaInicial.txt").toString();
        cargarDatos(archivo);
        // System.out.println(mapaRutas.toString());
        // System.out.println(diccionario.toString());
        //System.out.println(  infoClientes.toString());
       Lista nueva= infoClientes.obtenerConjuntoRangos();
    }

    public static void cargarDatos(String archivo) {
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
                    default:
                        System.out.println("Formato desconocido en la línea: " + linea + " " + i);
                        break;
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static String mostrarInfoCiudad(Comparable codigoPostal ) {
        // Dado un código postal de una ciudad, mostrar toda su información
        return (diccionario.obtenerDato(codigoPostal).toString());
    }

    public static Lista mostrarCiudadesPrefijo(int prefijo) {
        return diccionario.ciudadesPrefijo(prefijo);
    }

}
