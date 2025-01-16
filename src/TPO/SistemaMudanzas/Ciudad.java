package TPO.SistemaMudanzas;

public class Ciudad {
    int codigoPostal;
    String nombre, provincia;

    public Ciudad(int cp, String n, String p) {
        codigoPostal = cp;
        nombre = n.trim();
        provincia = p.trim();
    }
    

    // sets y gets
    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
    public String toString() {
        return  "||"+this.nombre + "||" + this.provincia + "||" + this.codigoPostal;
    }
    
}
