package TPO.SistemaMudanzas;

import java.time.LocalDate;

public class SolicitudViaje {
    int codigoPostalOrigen, codigoPostalDestino;
    int idCliente, cantBultos;
    double metrosCubicos;
    String domicilioRetiro, domicilioEntrega;
    String fecha,tipoDocumento;
    boolean pagado;

    public SolicitudViaje(int codigoPostalOrigen, int codigoPostalDestino,String fecha ,String tipoDocumento,int idCliente, int cantBultos, double metrosCubicos, String domicilioRetiro, String domicilioEntrega,boolean pagado) {
        this.codigoPostalOrigen = codigoPostalOrigen;
        this.codigoPostalDestino = codigoPostalDestino;
        this.tipoDocumento=tipoDocumento;
        this.idCliente = idCliente;
        this.cantBultos = cantBultos;
        this.metrosCubicos = metrosCubicos;
        this.domicilioRetiro = domicilioRetiro;
        this.domicilioEntrega = domicilioEntrega;
        this.fecha = fecha;
        this.pagado=pagado;
    }

    public int getCodigoPostalOrigen() {
        return codigoPostalOrigen;
    }

    public void setCodigoPostalOrigen(int codigoPostalOrigen) {
        this.codigoPostalOrigen = codigoPostalOrigen;
    }

    public int getCodigoPostalDestino() {
        return codigoPostalDestino;
    }

    public void setCodigoPostalDestino(int codigoPostalDestino) {
        this.codigoPostalDestino = codigoPostalDestino;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getCantBultos() {
        return cantBultos;
    }

    public void setCantBultos(int cantBultos) {
        this.cantBultos = cantBultos;
    }

    public double getMetrosCubicos() {
        return metrosCubicos;
    }

    public void setMetrosCubicos(double metrosCubicos) {
        this.metrosCubicos = metrosCubicos;
    }

    public String getDomicilioRetiro() {
        return domicilioRetiro;
    }

    public void setDomicilioRetiro(String domicilioRetiro) {
        this.domicilioRetiro = domicilioRetiro;
    }

    public String getDomicilioEntrega() {
        return domicilioEntrega;
    }

    public void setDomicilioEntrega(String domicilioEntrega) {
        this.domicilioEntrega = domicilioEntrega;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String toString() {
        return "Solicitud de Viaje: Código Postal Origen = " + codigoPostalOrigen + ", Código Postal Destino = " + codigoPostalDestino + 
               ", Fecha = " + fecha + ", Tipo de Documento = " + tipoDocumento + ", ID Cliente = " + idCliente + 
               ", Cantidad de Bultos = " + cantBultos + ", Metros Cúbicos = " + metrosCubicos + 
               ", Domicilio de Retiro = " + domicilioRetiro + ", Domicilio de Entrega = " + domicilioEntrega + 
               ", Pagado = " + (pagado ? "Sí" : "No");
    }
    
}
