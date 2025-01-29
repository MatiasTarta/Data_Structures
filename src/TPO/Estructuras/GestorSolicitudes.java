package TPO.Estructuras;
import TPO.SistemaMudanzas.SolicitudViaje;
import lineales.dinamicas.Cola;
public class GestorSolicitudes extends Cola {
    //S;7100;9000;17/06/2023;PAS;35761234;10;4;Calle Larga 2021;Trelew 205;F
    public void cargarSolicitud(int codigoOrigen,int codigoDestino,String fecha,String tipoDocumento,int numeroDocumento,int bultos,double espacio,String domicilioRetiro,String domicilioEntrega,boolean pago){
        SolicitudViaje aux= new SolicitudViaje(codigoOrigen, codigoDestino, fecha, tipoDocumento, numeroDocumento, bultos, espacio, domicilioRetiro, domicilioEntrega,pago);
        this.poner(aux);
    }

    @Override
    public String toString() {
        StringBuilder cadena = new StringBuilder();
        while (!esVacia()) {
            // Usar super.sacar() para obtener el siguiente elemento de la cola
            SolicitudViaje solicitud = (SolicitudViaje) super.obtenerFrente();  // Obtener el frente sin eliminarlo
            // Añadir la representación de la solicitud
            cadena.append(solicitud.toString()).append("\n");
            // Ahora sacar el elemento de la cola
            super.sacar();
        }
        return cadena.toString();
    }
    




    
}
