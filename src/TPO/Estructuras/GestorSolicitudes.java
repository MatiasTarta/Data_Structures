package TPO.Estructuras;
import TPO.SistemaMudanzas.SolicitudViaje;
import lineales.dinamicas.Lista;
public class GestorSolicitudes {
    Diccionario ciudadesPartida;
    Diccionario ciudadesLLegada;

    public GestorSolicitudes(){
        ciudadesPartida=new Diccionario();
        ciudadesLLegada= new Diccionario();
    }
    //S;7100;9000;17/06/2023;PAS;35761234;10;4;Calle Larga 2021;Trelew 205;F
    public void cargarSolicitud(int codigoOrigen,int codigoDestino,String fecha,String tipoDocumento,int numeroDocumento,int bultos,double espacio,String domicilioRetiro,String domicilioEntrega,boolean pago){
        SolicitudViaje aux= new SolicitudViaje(codigoOrigen, codigoDestino, fecha, tipoDocumento, numeroDocumento, bultos, espacio, domicilioRetiro, domicilioEntrega,pago);
        if (ciudadesPartida.existeClave(codigoOrigen)) {
            //si la ciudad existe directamente cargo la solicitud en la lista ya existente del nodo
            ciudadesPartida.cargarSolicitud(codigoOrigen, aux);
        }else{
            Lista solicitudesAsociadas= new Lista();
            solicitudesAsociadas.insertar(aux, solicitudesAsociadas.longitud()+1);
            ciudadesPartida.insertar(codigoOrigen, solicitudesAsociadas);
        }
        if (ciudadesLLegada.existeClave(codigoOrigen)) {
            //si la ciudad existe directamente cargo la solicitud en la lista ya existente del nodo
            ciudadesPartida.cargarSolicitud(codigoOrigen, aux);
        }else{
            Lista solicitudesAsociadas= new Lista();
            solicitudesAsociadas.insertar(aux, solicitudesAsociadas.longitud()+1);
            ciudadesLLegada.insertar(codigoOrigen, solicitudesAsociadas);
        }
    }

    public String toString() {
        return "Ciudades Partida: " + ciudadesPartida.toString() + "\nCiudades Llegada: " + ciudadesLLegada.toString();
    }
    




    
}
