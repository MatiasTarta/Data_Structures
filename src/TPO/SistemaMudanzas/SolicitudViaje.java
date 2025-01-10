package TPO.SistemaMudanzas;

import java.time.LocalDate;

public class SolicitudViaje {
    int codigoPostalOrigen, codigoPostalDestino;
    int idCliente, cantBultos;
    double metrosCubicos;
    String domicilioRetiro, domicilioEntrega;
    LocalDate fecha;

    public SolicitudViaje(int codigoPostalOrigen, int codigoPostalDestino, int idCliente, int cantBultos, double metrosCubicos, String domicilioRetiro, String domicilioEntrega, LocalDate fecha) {
        this.codigoPostalOrigen = codigoPostalOrigen;
        this.codigoPostalDestino = codigoPostalDestino;
        this.idCliente = idCliente;
        this.cantBultos = cantBultos;
        this.metrosCubicos = metrosCubicos;
        this.domicilioRetiro = domicilioRetiro;
        this.domicilioEntrega = domicilioEntrega;
        this.fecha = fecha;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
