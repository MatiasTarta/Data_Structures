package TPO.Estructuras;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import TPO.SistemaMudanzas.Ciudad;
import lineales.dinamicas.Lista;
import TPO.Estructuras.ArbolAVL;
public class TestCarga {
    public static void main(String[] args) {
        GrafoEtiquetado mapaRutas = new GrafoEtiquetado();
        ArbolAVL diccionario = new ArbolAVL();
        String archivo = Paths.get("src", "TPO", "SistemaMudanzas", "CargaInicial.txt").toString();
        cargarDatos(archivo,mapaRutas,diccionario);
        // System.out.println(mapaRutas.toString());
        //System.out.println(diccionario.toString()); 
    }

    public static void cargarDatos(String archivo,GrafoEtiquetado mapa,ArbolAVL diccionario){
        int i=1;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                switch (partes[0]) {
                    case "C":
                        cargarCiudad(Integer.parseInt(partes[1]), partes[2], partes[3],diccionario);
                        break;
                    case "R":
                        insertarRuta( Integer.parseInt(partes[1]),Integer.parseInt(partes[2]),Double.parseDouble(partes[3]), mapa);
                        break;
                    default:
                        System.out.println("Formato desconocido en la línea: " + linea+" "+i);
                        break;
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertarRuta(int codigoOrigen ,int codigoDestino,  double distancia,GrafoEtiquetado mapa) {
        if (!mapa.existeVertice(codigoOrigen)) {
            mapa.insertarVertice(codigoOrigen);
        }
        
        if (!mapa.existeVertice(codigoDestino)) {
            mapa.insertarVertice(codigoDestino);
        }
        
        // Solo agregar el arco si ambos nodos han sido insertados
        if (mapa.existeVertice(codigoOrigen) && mapa.existeVertice(codigoDestino)) {
            mapa.insertarArco(codigoOrigen, codigoDestino, distancia);
        }
    }

    public static void cargarCiudad(int codigo,String nombre,String provincia,ArbolAVL diccionario){
        Ciudad ciudad= new Ciudad(codigo, nombre, provincia);
        boolean exito= diccionario.insertar(codigo, ciudad);
        if (exito) {
            
        }
    }

    
}
