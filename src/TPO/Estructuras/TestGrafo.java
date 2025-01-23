package TPO.Estructuras;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import TPO.SistemaMudanzas.Ciudad;

public class TestGrafo {
    public static void main(String[] args) {
        GrafoEtiquetado mapaRutas = new GrafoEtiquetado();
        String archivo = Paths.get("src", "TPO", "SistemaMudanzas", "CargaInicial.txt").toString();
        cargarMapa(archivo,mapaRutas);
        System.out.println(mapaRutas.toString());
        System.out.println(mapaRutas.cantidadNodos());
        
    }

    public static void cargarMapa(String archivo,GrafoEtiquetado mapa){
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                switch (partes[0]) {
                    case "C":
                        //
                        break;
                    case "R":
                        insertarRuta(partes, mapa);
                        break;
                    default:
                        System.out.println("Formato desconocido en la línea: " + linea);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertarRuta(String[] partes,GrafoEtiquetado mapa){
        int codigoOrigen= Integer.parseInt(partes[1]);
        int codigoDestino = Integer.parseInt(partes[2]);
        double distancia =  Double.parseDouble(partes[3]);
        if (!mapa.existeVertice(codigoOrigen) && !mapa.existeVertice(codigoDestino)) {
          //verifica que no esten ya en el grafo
          boolean yaExiste= mapa.existeVertice(codigoOrigen) && mapa.existeVertice(codigoDestino);
         if (yaExiste) {
            mapa.insertarVertice(codigoOrigen);
            mapa.insertarVertice(codigoDestino);
            mapa.insertarArco(codigoOrigen, codigoDestino, distancia);
         }
        }
    }
}
