package TPO.Estructuras; 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import TPO.SistemaMudanzas.Ciudad;
import lineales.dinamicas.Lista;
public class PrubeaCarga {
    public static void main(String[] args) {
        Diccionario diccionarioCiudades= new Diccionario();
        String archivo = Paths.get("src", "TPO", "SistemaMudanzas", "CargaInicial.txt").toString();
        leerCiudades(archivo,diccionarioCiudades);
        System.out.println(diccionarioCiudades.toString());
    }

    public static void leerCiudades(String archivo, Diccionario diccionario) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("C")) {
                    Ciudad ciudadAux = stringACiudad(linea);
                    diccionario.insertar((Comparable) ciudadAux.getCodigoPostal(), ciudadAux);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static Ciudad stringACiudad(String ciudadString){
         String[] partes = ciudadString.split(";");
         int codigoPostal = Integer.parseInt(partes[1]);
         String nombre = partes[2];
         String provincia = partes[3];
         return new Ciudad(codigoPostal, nombre, provincia);
    }
}
