package TPO.SistemaMudanzas;
import java.util.*;
public class MudanzasCompartidas {
   public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int entrada=0;

    System.out.println("Bienvenido al sistema de Mudanzas de la Empresa");


    System.out.println("Que desea Hacer a Continuacion?");
    while (entrada==0) {
    System.out.println("Ingrese 1 para realizar una carga Inicial de un lote fijo");
    System.out.println("Ingrese 2 para tratar Altas,Bajas y Modificaiones del sistema");
    System.out.println("Ingrese 3 para realizar consultas");
    System.out.println("Ingrese 4 para mostrar las estructuras del sistema");
    entrada=sc.nextInt();
    switch (entrada) {
        case 1:
            //carga inicial
            break;
        case 2:
            //abm
            break;
        case 3:
            //consultas
            break;
        case 4:
            //muestra estructuras del sistema
            break;
        default:
            break;
    }
    }


   }

}
