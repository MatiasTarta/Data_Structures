package TPO.Estructuras;
import TPO.SistemaMudanzas.SolicitudViaje;
import java.util.HashMap;

public class GestorSolicitudes {
    private HashMap<Integer, SolicitudViaje> mapaSolicitudes;

    public GestorSolicitudes(){
        mapaSolicitudes = new HashMap<>();
    }

    public void cargarSolicitud(int codigoOrigen, int codigoDestino, String fecha, String tipoDocumento, int numeroDocumento, int bultos, double espacio, String domicilioRetiro, String domicilioEntrega, boolean pago){
        SolicitudViaje aux = new SolicitudViaje(codigoOrigen, codigoDestino, fecha, tipoDocumento, numeroDocumento, bultos, espacio, domicilioRetiro, domicilioEntrega, pago);
        mapaSolicitudes.put(aux.getIdSolicitud(), aux);
    }

    public SolicitudViaje obtenerSolicitud(int idSolicitud) {
        return mapaSolicitudes.get(idSolicitud);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for (Integer key : mapaSolicitudes.keySet()) {
            SolicitudViaje solicitud = mapaSolicitudes.get(key);
            sb.append("Solicitud ID: ").append(key)
              .append("\nTipo Documento: ").append(solicitud.getTipoDocumento())
              .append("\nDNI Cliente: ").append(solicitud.getIdCliente())
              .append("\nFecha: ").append(solicitud.getFecha())
              .append("\nCódigo Postal Origen: ").append(solicitud.getCodigoPostalOrigen())
              .append("\nCódigo Postal Destino: ").append(solicitud.getCodigoPostalDestino())
              .append("\nDomicilio Retiro: ").append(solicitud.getDomicilioRetiro())
              .append("\nDomicilio Entrega: ").append(solicitud.getDomicilioEntrega())
              .append("\nCantidad de Bultos: ").append(solicitud.getCantBultos())
              .append("\nMetros Cúbicos: ").append(solicitud.getMetrosCubicos())
              .append("\nPagado: ").append(solicitud.isPagado() ? "Sí" : "No")
              .append("\n\n");
        }
        
        return sb.toString();
    }
    
}
