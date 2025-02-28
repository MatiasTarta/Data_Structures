package TPO.Estructuras;
public class Clave {
    //clase clave que codifica una clave a partir de numero documento + tipo documento
    private String tipoDocumento;
    private int numeroDocumento;

    public Clave(String tipo,int numero){
        tipoDocumento=tipo;
        numeroDocumento=numero;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public String toString(){
        return tipoDocumento+" "+numeroDocumento;
    }
    public int hashCode() {
    return Math.abs((tipoDocumento + numeroDocumento).hashCode() - 1);
    }

    public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Clave clave = (Clave) obj;
    return numeroDocumento == clave.numeroDocumento && tipoDocumento.equals(clave.tipoDocumento);
    }


 

}
