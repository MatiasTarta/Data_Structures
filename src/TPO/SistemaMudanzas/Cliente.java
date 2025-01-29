package TPO.SistemaMudanzas;

public class Cliente {
    int telefono, numeroDocumento;
    String nombre, apellido, email,tipoDocumento;

    public Cliente(String tipoDocumento, int numeroDocumento, String nombre, String apellido, int telefono,String email) {
        this.tipoDocumento = tipoDocumento;
        this. telefono = telefono;
        this.numeroDocumento = numeroDocumento;
        this. nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equivale(String nombre, String apellido, int telefono, String email){
        return this.nombre.equals(nombre) && this.apellido.equals(apellido) && this.telefono==telefono && this.email.equals(email);
    }

    public String toString() {
        return "TipoDocumento: " + tipoDocumento + ", NumeroDocumento: " + numeroDocumento +
               ",Nombre: " + nombre + ",Apellido: " + apellido +
               ",Telefono: " + telefono + ",Email: " + email;
    }
    
}
