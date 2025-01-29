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

    public double calculaEspacioNecesario(int codigoO, int codigoD) {
        Lista solicitudes = buscarSolicitudes(codigoO, codigoD);
        double espacio = 0;
        for (int i = 1; i <= solicitudes.longitud(); i++) {
            SolicitudViaje solicitud = (SolicitudViaje) solicitudes.recuperar(i);
            espacio += solicitud.getMetrosCubicos(); // suma el valor de metros cúbicos
        }
        return espacio;
    }
    
    public boolean verificarEspacioDisponible(int codigoO, int codigoD, double cantidad) {
        final double MAXIMO_CAMION = 20.0;//maximo de carga de un camion
        double espacioActual = calculaEspacioNecesario(codigoO, codigoD);
        
        // Comprobamos si la suma del espacio actual y la cantidad excede el máximo permitido
        return (espacioActual + cantidad) <= MAXIMO_CAMION;
    }
    




    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        // Iterar sobre las claves y sus listas de solicitudes
        for (Pair clave : mapaSolicitudes.keySet()) {
            sb.append("Origen: ").append(clave.getCodigoPostalOrigen())
              .append(", Destino: ").append(clave.getCodigoPostalDestino())
              .append("\nSolicitudes:\n");
            
            Lista listaSolicitudes = mapaSolicitudes.get(clave);
            
            // Iterar sobre la lista de solicitudes
            for (int i = 1; i <= listaSolicitudes.longitud(); i++) {
                sb.append(listaSolicitudes.recuperar(i).toString())  // Supone que SolicitudViaje tiene su propio toString
                  .append("\n");
            }
        }
        
        return sb.toString();
    }
    
    

    
}
