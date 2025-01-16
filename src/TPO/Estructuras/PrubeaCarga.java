package TPO.Estructuras; 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import TPO.SistemaMudanzas.Ciudad;
import lineales.dinamicas.Lista;
public class PrubeaCarga {
    public static void main(String[] args) {
        String archivo = Paths.get("src", "TPO", "SistemaMudanzas", "CargaInicial.txt").toString();
        Lista nuevaLista= leerCiudades(archivo);
        System.out.println(nuevaLista.toStringSaltoLinea());

    }

    public static Lista leerCiudades(String archivo){
        Lista listaCiudades= new Lista();
        int i=1;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("C")) {  // Verifica que el primer caracter sea 'C'
                    Ciudad ciudadAux= stringACiudad(linea);
                    listaCiudades.insertar(ciudadAux, i);
                    i++;
                } else {
                    break;  // Sale del bucle si la línea no empieza con 'C'
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaCiudades;
    }

    public static Ciudad stringACiudad(String ciudadString){
         String[] partes = ciudadString.split(";");
         int codigoPostal = Integer.parseInt(partes[1]);
         String nombre = partes[2];
         String provincia = partes[3];
         return new Ciudad(codigoPostal, nombre, provincia);
    }
}
