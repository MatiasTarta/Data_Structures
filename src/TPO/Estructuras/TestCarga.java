package TPO.Estructuras;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import TPO.SistemaMudanzas.Ciudad;
import TPO.SistemaMudanzas.SolicitudViaje;
import lineales.dinamicas.Lista;

public class TestCarga {
    public static GrafoEtiquetado mapaRutas = new GrafoEtiquetado();
    public static Diccionario diccionario = new Diccionario();
    private static MapeoAMuchos infoClientes= new MapeoAMuchos(5);
    private static GestorSolicitudes solicitudes = new GestorSolicitudes();
    public static void main(String[] args) {
        String archivo = Paths.get("src", "TPO", "SistemaMudanzas", "CargaInicial.txt").toString();
        cargarDatos(archivo);
        //System.out.println(mapaRutas.toString());
        //System.out.println(diccionario.toString());
        //System.out.println(  infoClientes.toString());
        //System.out.println(solicitudes.toString());
       
    }

    public static boolean caminoPerfecto(Lista ciudades, double metrosCubicos) {
        boolean exito = false;
        int i = 2;
        while (i <= ciudades.longitud() && !exito) {
            Ciudad origen = (Ciudad) ciudades.recuperar(1);  // Ciudad origen
            int codigoO = origen.getCodigoPostal();
            Ciudad destino = (Ciudad) ciudades.recuperar(i);  // Ciudad destino
            int codigoD = destino.getCodigoPostal();
            Lista viajes = solicitudes.buscarSolicitudes(codigoO, codigoD);
            if (!viajes.esVacia()) {
                //hay al menos una solicitud
                SolicitudViaje aux= (SolicitudViaje) viajes.recuperar(1);
                metrosCubicos+= aux.getMetrosCubicos();
                if (metrosCubicos<=80.0) {
                    exito = true;//40 es el maximo de metros cubicos de capacidad del camion
                }
                ciudades.eliminar(1);  // Eliminar la primera ciudad
            }
            i++;
        }
        // Si  se encontró un camino perfecto continua de forma recursiva
        if (exito && ciudades.longitud() > 1) {
            exito = caminoPerfecto(ciudades, metrosCubicos);  // Llamada recursiva con el resto de las ciudades
        }
        return exito;
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
