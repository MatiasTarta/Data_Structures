package TPO.SistemaMudanzas;

public class Cliente {
    char tipoDocumento;
    int telefono, numeroDocumento;
    String nombre, apellido, email;

    public Cliente(char tipo, int t, int n, String nom, String ap, String mail) {
        tipoDocumento = tipo;
        telefono = t;
        numeroDocumento = n;
        nombre = nom;
        apellido = ap;
        email = mail;
    }

    public char getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(char tipoDocumento) {
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

    public String toString() {
        return "Cliente: Tipo de Documento = " + tipoDocumento + ", Numero Documento = " + numeroDocumento +
               ", Nombre = " + nombre + ", Apellido = " + apellido + 
               ", Telefono = " + telefono + ", Email = " + email;
    }
    
}
