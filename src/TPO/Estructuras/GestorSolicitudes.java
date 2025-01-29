package TPO.Estructuras;

import TPO.SistemaMudanzas.SolicitudViaje;
import java.util.HashMap;
import lineales.dinamicas.Lista;

public class GestorSolicitudes {
    private final HashMap<Pair, Lista> mapaSolicitudes;

    public GestorSolicitudes() {
        this.mapaSolicitudes = new HashMap<>();
    }

    public void cargarSolicitud(int codigoOrigen, int codigoDestino, String fecha, String tipoDocumento, int numeroDocumento, int bultos, double espacio, String domicilioRetiro,String domicilioEntrega, boolean pago) {
        SolicitudViaje solicitud = new SolicitudViaje(codigoOrigen, codigoDestino, fecha, tipoDocumento, numeroDocumento, bultos, espacio, domicilioRetiro,domicilioEntrega, pago);
        Pair clave = new Pair(codigoOrigen, codigoDestino);
        
        // Usamos la clase Lista para almacenar las solicitudes
        mapaSolicitudes.putIfAbsent(clave, new Lista());//si no hay nada crea una lista
        mapaSolicitudes.get(clave).insertar(solicitud, mapaSolicitudes.get(clave).longitud() + 1);//si habia alguna solicitud ya cargada por esas ciudades enlaza las solicitudes
    }

    public Lista buscarSolicitudes(int codigoOrigen, int codigoDestino) {
        Pair clave = new Pair(codigoOrigen, codigoDestino);
        // Crear la variable 'nueva' y asignarle el valor encontrado o una lista vacía
        Lista nueva = mapaSolicitudes.getOrDefault(clave, new Lista());
        // Retornar la variable 'nueva'
        return nueva;
    }
    
}
