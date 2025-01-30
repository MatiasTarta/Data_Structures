package TPO.SistemaMudanzas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import TPO.Estructuras.Diccionario;
import TPO.Estructuras.GestorSolicitudes;
import TPO.Estructuras.GrafoEtiquetado;
import TPO.Estructuras.MapeoAMuchos;
import lineales.dinamicas.Lista;

public class MudanzasCompartidas {
    private static Diccionario diccionario = new Diccionario();
    private static GrafoEtiquetado mapaRutas = new GrafoEtiquetado();
    private static MapeoAMuchos infoClientes= new MapeoAMuchos(12);
    private static GestorSolicitudes solicitudes=new GestorSolicitudes();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int entrada = 1;
        String archivo = Paths.get("src", "TPO", "SistemaMudanzas", "CargaInicial.txt").toString();

        hacerCargaInicial(archivo);
        System.out.println("Bienvenido al sistema de Mudanzas de la Empresa");
        System.out.println("Que desea Hacer a Continuacion?");
        while (entrada != 0) {
            System.out.println("<------------------Menu Principal------------------>");
            System.out.println("Ingrese 1 para realizar una carga Inicial de un lote fijo");
            System.out.println("Ingrese 2 para tratar Altas,Bajas y Modificaiones del sistema");
            System.out.println("Ingrese 3 para realizar consultas");
            System.out.println("Ingrese 4 para mostrar las estructuras del sistema");
            System.out.println("Ingrese 0 para cerrar");
            System.out.println("<-------------------------------------------------->");
            entrada = sc.nextInt();
            System.out.println("\n");
            switch (entrada) {
                case 1:
                    // carga inicial
                    hacerCargaInicial(archivo);
                    break;
                case 2:
                    menuABM(sc, 1);
                    break;
                case 3:
                    // consultas
                    menuConsultas(sc, 1);
                    break;
                case 4:
                    menuVisualizacion(sc,1);
                    break;
                default:
                    break;
            }
        }

    }

    public static void menuABM(Scanner sc,int entrada){
        while (entrada!=0) {
            System.out.println("<------------------Menu ABM------------------>");
            System.out.println("1. Altas/Bajas/Modificaciones de Ciudades");
            System.out.println("2. Altas/Bajas/Modificaciones de Rutas");
            System.out.println("3. Altas/Bajas/Modificaciones de Clientes");
            System.out.println("4. Altas/Bajas/Modificaciones de Solicitudes");
            System.out.println("0.CERRAR");
            entrada=sc.nextInt();
            switch (entrada) {
                case 1:
                    abmCiudades(sc, 1);
                    break;
                case 2:
                    abmRutas(sc, 1);
                    break;
                case 3:
                    abmRutas(sc, 1);
                    break;
                case 4:
                    abmSolicitudes(sc, 1);
                    break;
                default:
                    break;
            }
        }
    }

    public static void abmCiudades(Scanner sc,int entrada){
        while (entrada!=0) {
            System.out.println("<------------------Menu ABM CIUDADES------------------>");
            System.out.println("1. Altas de Ciudades");
            System.out.println("2. /Bajas de Ciudades");
            System.out.println("3. /Modificaciones de Ciudades");
            System.out.println("0.CERRAR");
            entrada=sc.nextInt();
            switch (entrada) {
                case 1:
                    System.out.println("Ingrese codigo Postal");
                    int codigoA= sc.nextInt();
                    System.out.println("Ingrese Nombre de la Ciudad");
                    String nombreA= sc.next();
                    System.out.println("Ingrese Provincia");
                    String provinciaA= sc.next();
                    diccionario.insertar(codigoA, new Ciudad(codigoA, nombreA, provinciaA));
                    break;
                case 2:
                    System.out.println("Ingrese la ciudad que quiere eliminar");
                    int codigoB= sc.nextInt();
                    diccionario.eliminar(codigoB);
                    break;
                case 3:
                    System.out.println("Ingrese el codigo postal de la ciudad que desea modificar");
                    int codigoC= sc.nextInt();
                    Ciudad c = (Ciudad) diccionario.obtenerDato(codigoC);
                    System.out.println("Que desea modificar");
                    System.out.println("1. Nombre de la Ciudad");
                    System.out.println("2. Provincia de la ciudad");
                    int entradaB=sc.nextInt();
                    switch (entradaB) {
                        case 1:
                            System.out.println("Ingrese el nuevo nombre");
                            String nombreC= sc.next();
                            c.setNombre(nombreC);
                            break;
                        case 2:
                            System.out.println("Ingrese la nueva provincia");
                            String provinciaC= sc.next();
                            c.setProvincia(provinciaC);
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public static void abmRutas(Scanner sc,int entrada){
        while (entrada!=0) {
            System.out.println("<------------------Menu ABM RUTAS------------------>");
            System.out.println("1. Altas de Rutas");
            System.out.println("2. /Bajas de Rutas");
            System.out.println("3. /Modificaciones de Rutas");
            System.out.println("0.CERRAR");
            entrada=sc.nextInt();
            switch (entrada) {
                case 1: 
                    System.out.println("Ingrese la ciudad Origen de la Ruta");
                    int codigoA=sc.nextInt();
                    System.out.println("Ingrese la ciudad Destino de la ruta");
                    int codigoB=sc.nextInt();
                    System.out.println("Ingrese distancia en km entre las ciudades");
                    double distancia=sc.nextDouble();
                    try {
                        Ciudad origen = (Ciudad) diccionario.obtenerDato(codigoA);
                        Ciudad destino = (Ciudad) diccionario.obtenerDato(codigoB);
                        //esto es Solamente para que se puedan agregar rutas con ciudades ya insertadas en el diccionario, en realidad las variables no se usan
                    } catch (ClassCastException e) {
                        System.out.println("Error. Ingrese una ciudad existente en el diccionario");
                    }
                    insertarRuta(codigoA, codigoB, distancia);
                    break;
                case 2:
                    System.out.println("Ingrese la ciudad de Origen de la ruta que quiere eliminar");
                    int origenB= sc.nextInt();
                    System.out.println("Ingrese la ciudad de Destino de la ruta que quiere eliminar");
                    int destinoB=sc.nextInt();
                    mapaRutas.eliminarVertice(origenB);
                    mapaRutas.eliminarVertice(destinoB);
                    mapaRutas.eliminarArco(origenB, destinoB);
                    break;
                case 3:
                    System.out.println("Ingrese la ciudad de Origen de la ruta que quiere modificar");
                    int origenC= sc.nextInt();
                    System.out.println("Ingrese la ciudad de Destino de la ruta que quiere modificar");
                    int destinoC=sc.nextInt();
                    System.out.println("Ingrese la nueva distancia en km que hay entre las ciduades");
                    double distanciaC= sc.nextDouble();
                    mapaRutas.eliminarArco(origenC, destinoC);
                    mapaRutas.insertarArco(origenC, destinoC, distanciaC);
                    break;
                default:
                    break;
            }
        }
    }

    public static void abmClientes(Scanner sc,int entrada){
        while (entrada!=0) {
            System.out.println("<------------------Menu ABM CLIENTES------------------>");
            System.out.println("1.  Altas de Clientes");
            System.out.println("2.  Bajas de Clientes");
            System.out.println("3.  Modificaciones de Clientes");
            System.out.println("0.CERRAR");
            entrada=sc.nextInt();
            switch (entrada) {
                case 1://P;DNI;34871234;MONTES;ANDRES IGNACIO;299456321;andres.montes@gmail.com
                    System.out.println("Ingrese el tipo de Documento del cliente nuevo");
                    String tipoA=sc.next();
                    System.out.println("Ingrese el numero de Documento del cliente nuevo");
                    int numeroA=sc.nextInt();
                    System.out.println("Ingrese el apellido del cliente");
                    String nombreA=sc.next();
                    System.out.println("Ingrese el apellido del cliente");
                    String apellidoA=sc.next();
                    System.out.println("Ingrese el telefono del cliente");
                    int telefonoA=sc.nextInt();
                    System.out.println("Ingrese el mail del cliente");
                    String emailA=sc.next();
                    infoClientes.asociar(tipoA, numeroA, nombreA, apellidoA, telefonoA, emailA);
                    break;
                case 2:
                    System.out.println("Ingrese tipo de Documento del Cliente a dar de Baja");
                    String tipoB= sc.next();
                    System.out.println("Ingrese el numero de documento del Cliente a dar de baja");
                    int numeroB=sc.nextInt();
                    infoClientes.desacoiar(tipoB, numeroB);
                    break;
                case 3:
                    System.out.println("Ingrese tipo de Documento del Cliente a modificar");
                    String tipoC= sc.next();
                    System.out.println("Ingrese el numero de documento del Cliente a modificar");
                    int numeroC=sc.nextInt();
                    Cliente aux = (Cliente) infoClientes.obtenerValor(tipoC, numeroC);
                    System.out.println("Que desea modificar del cliente "+aux.toString());
                    System.out.println("1.Nombre");
                    System.out.println("2.Apellido");
                    System.out.println("3.Telefono");
                    System.out.println("4.Email");
                    int modificacion=sc.nextInt();
                        switch (modificacion) {
                            case 1:
                                System.out.println("Ingrese el nombre del cliente");
                                String nombreC = sc.next();
                                aux.setNombre(nombreC);
                                    break;
                            case 2:
                                System.out.println("Ingrese el apellido del cliente");
                                String apellidoC = sc.next();
                                aux.setApellido(apellidoC);
                                    break;
                            case 3:
                                System.out.println("Ingrese el teléfono del cliente");
                                int telefonoC = sc.nextInt();
                                aux.setTelefono(telefonoC);
                                    break;
                            case 4:
                                System.out.println("Ingrese el email del cliente");
                                String emailC = sc.next();
                                aux.setEmail(emailC);
                                break;
                            default:
                                break;
                        }
                    break;
                default:
                    break;
            }
        }
    }

    public static void abmSolicitudes(Scanner sc,int entrada){
        while (entrada!=0) {
            System.out.println("<------------------Menu ABM SOLICITUDES------------------>");
            System.out.println("1. Altas de Solicitudes");
            System.out.println("2. /Bajas de Solicitudes");
            System.out.println("3. /Modificaciones de Solicitudes");
            System.out.println("0.CERRAR");
            entrada=sc.nextInt();
            switch (entrada) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                default:
                    break;
            }
        }
    }

    public static void menuConsultas(Scanner sc, int entrada) {
        System.out.println("Bienvenido al menu de Consultas");
        System.out.println("Que desea Hacer a Continuacion?");
        while (entrada != 0) {
            System.out.println("<------------------Menu Consultas------------------>");
            System.out.println("Ingrese 1 para realizar consultas sobre los clientes");
            System.out.println("Ingrese 2 para realizar consultas sobre las ciudades");
            System.out.println("Ingrese 3 para realizar consultas sobre los viajes");
            System.out.println("Ingrese 4 para verificar un viaje");
            System.out.println("Ingrese 0 para cerrar");
            System.out.println("<-------------------------------------------------->");
            entrada = sc.nextInt();
            System.out.println("\n");
            switch (entrada) {
                case 1:
                System.out.println("Dada una clave de un Cliente muestra toda la informacion sobre el");
                System.out.println("Ingrese el tipo de Documento del cliente:");
                String tipo = sc.next();
                sc.nextLine();
                System.out.println("Ingrese el numero de Documento del cliente");
                int numero = sc.nextInt();
                System.out.println((infoClientes.obtenerValor(tipo, numero)).toString());
                    break;
                case 2:
                    menuConsultasCiudades(sc, 1);
                    break;
                case 3:
                    // consultas sobre mapa
                    menuConsultasMapa(sc, 1);
                    break;
                case 4:
                    menuVerificacionViajes(sc, 1);
                    break;
                default:
                    break;
            }
        }
    }

    public static void menuConsultasCiudades(Scanner sc, int entrada){
        while (entrada != 0) {
            System.out.println("1.Dado un código postal de una ciudad, mostrar toda su información");
            System.out.println("2.Dado un prefijo, devolver todas las ciudades cuyo código postal comienza con dicho prefijo");
            System.out.println("Ingrese 0 para cerrar");
            entrada = sc.nextInt();
            System.out.println("\n");
            switch (entrada) {
                case 1:
                    System.out.println("Ingrese el codigo Postal");
                    int codigo = sc.nextInt();
                    System.out.println(mostrarCiudadesPrefijo(codigo));
                    break;
                case 2:
                    System.out.println("Ingrese el prefijo");
                    int prefijo = sc.nextInt();
                    System.out.println(mostrarCiudadesPrefijo(prefijo).toString());
                    break;
                default:
                    break;
            }
        }
    }

    public static void menuConsultasMapa(Scanner sc, int entrada){
        while (entrada != 0){
            System.out.println("1. Obtener el camino mas directo");//mas directo= pasa por menos ciudades
            System.out.println("2. Obtener el camino mas corto en distancia");
            System.out.println("3. Obtener todos los caminos posibles entre dos ciudades pasando por una ciudad Intermedia");
            System.out.println("4. Verificar si es posible viajar entre 2 ciudades con una cantidad maxima de Kilometros");
            System.out.println("Ingrese 0 para cerrar");
            entrada= sc.nextInt();
            switch (entrada) {
                case 1:
                    System.out.println("Ingrese Ciudad de Origen");
                    int origen=sc.nextInt();
                    System.out.println("Ingrese ciudad de Destino");
                    int destino=sc.nextInt();
                    System.out.println("El camino mas directo desde "+origen+" hasta "+destino+" es:");
                    System.out.println((mapaRutas.caminoMasCorto(origen, destino)).toString());
                    break;
                case 2:
                    System.out.println("Ingrese Ciudad de Origen");
                    int origenA=sc.nextInt();
                    System.out.println("Ingrese ciudad de Destino");
                    int destinoA=sc.nextInt();
                    System.out.println("El camino mas corto desde "+origenA+" hasta "+destinoA+" es:");
                    System.out.println((mapaRutas.caminoMenorDistancia(origenA, destinoA)).toString());
                    break;
                case 3:
                    //hay que arreglar el modulo
                    break;
                case 4:
                System.out.println("Ingrese Ciudad de Origen");
                int origenC=sc.nextInt();
                System.out.println("Ingrese ciudad de Destino");
                int destinoC=sc.nextInt();
                System.out.println("Ingrese el maximo de kilometros");
                int km=sc.nextInt();
                Lista nueva=mapaRutas.caminoMayorDistancia(origenC, destinoC, km);
                if ((nueva).esVacia()) {
                    System.out.println("No es posible viajar entre esas 2 ciudades recorriendo como maximo "+km+" kilometros");
                }else{
                    System.out.println("Es posible viajar entre las dos ciudades Siguiendo el camino:");
                    System.out.println(nueva.toString());
                }
                    break;
                default:
                    break;
            }
        }
    }

    public static void menuVerificacionViajes(Scanner sc,int entrada){
        while (entrada!=0) {
            System.out.println("1.Dadas 2 ciudades muestra todos los pedidos y calcula el espacio nescesario en el camion");
            System.out.println("2.Dadas 2 ciudades y una cantidad de metros cubicos da todos las posibles solicitudes de viaje a ciudades intermedias");
            System.out.println("3.Dado un listado de ciudades verifica si existe un camino perfecto entre las ciduades.");
            System.out.println("0. Cerrar");
            entrada=sc.nextInt();
            switch (entrada) {
                case 1:
                    System.out.println("Ingrese la ciudad de origen(Codigo Postal)");
                    int origen = sc.nextInt();
                    System.out.println("Ingrese la ciudad de destino");
                    int destino = sc.nextInt();
                    double espacio= solicitudes.calculaEspacioNecesario(origen, destino);
                    System.out.println("Para cubrir las solicitudes entre "+origen+" y"+destino+" hacen falta "+espacio+" metros cubicos");
                    break;
                 case 2:
                 System.out.println("Ingrese la ciudad de origen(Codigo Postal)");
                 int origenB = sc.nextInt();
                 System.out.println("Ingrese la ciudad de destino");
                 int destinoB = sc.nextInt();
                 System.out.println("Ingrese los metros cubicos");
                 double metros = sc.nextDouble();
                 Lista caminos= solicitudesPosibles(origenB, destinoB,metros);
                 if (!caminos.esVacia()) {
                    System.out.println(caminos.toString());
                 }else{
                    System.out.println("O no sobra espacio o no hay solicitudes pendientes entre las ciudades ingresadas");
                 }
                    break;
                case 3:
                    System.out.println("Ingrese la cantidad de ciudades que quiera chequear");
                    int cantidad= sc.nextInt();
                    Lista ciudades= new Lista();
                    for (int i = 1; i <=cantidad; i++) {
                        System.out.println("Ingrese el codigo postal");
                        int codigoPostal= sc.nextInt();
                        ciudades.insertar(new Ciudad(codigoPostal, "", ""), ciudades.longitud()+1);
                    }
                    boolean caminoPerfecto= caminoPerfecto(ciudades, 0);
                    if (caminoPerfecto) {
                        System.out.println("Existe un camino perfecto entre las ciudades indicadas");
                    }else{
                        System.out.println("El camino entre las ciudades no es perfecto");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public static void menuVisualizacion(Scanner sc,int entrada){
        while (entrada!=0) {
            System.out.println("<------------------------->");
            System.out.println("Que estructura desea visualizar");
            System.out.println("1.Arbol de Ciudades");
            System.out.println("2.Grafo de Rutas");
            System.out.println("3.Mapeo de los clientes");
            System.out.println("4.Mapa de las solicitudes de viaje");
            entrada=sc.nextInt();
            switch (entrada) {
                case 1:
                    System.out.println(diccionario.toString());
                    break;
                case 2:
                    System.out.println(mapaRutas.toString());
                    break;
                case 3:
                    System.out.println(infoClientes.toString());
                    break;
                case 4:
                    System.out.println(solicitudes.toString());
                    break;
            
                default:
                    break;
            }
        }
    }

    public static void hacerCargaInicial(String archivo) {
        int i = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                switch (partes[0]) {
                    case "C":
                        cargarCiudad(Integer.parseInt(partes[1]), partes[2], partes[3]);
                        break;
                    case "R":
                        insertarRuta(Integer.parseInt(partes[1]), Integer.parseInt(partes[2]),
                                Double.parseDouble(partes[3]));
                        break;
                    case"P":
                        infoClientes.asociar(partes[1], Integer.parseInt(partes[2]), partes[3], partes[4], Integer.parseInt(partes[5]),partes[6]);
                        break;
                    case"S":
                        solicitudes.cargarSolicitud(Integer.parseInt(partes[1]), Integer.parseInt(partes[2]), partes[3],partes[4], Integer.parseInt(partes[5]), Integer.parseInt(partes[6]), Double.parseDouble(partes[7]), partes[8], partes[9], Boolean.parseBoolean(partes[10]));
                        break;
                    default:
                        System.out.println("Formato desconocido en la línea: " + linea + " " + i);
                        break;
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Carga inicial realizada");
    }

    public static void insertarRuta(int codigoOrigen, int codigoDestino, double distancia) {
        if (!mapaRutas.existeVertice(codigoOrigen)) {
            mapaRutas.insertarVertice(codigoOrigen);
        }

        if (!mapaRutas.existeVertice(codigoDestino)) {
            mapaRutas.insertarVertice(codigoDestino);
        }

        // Solo agregar el arco si ambos nodos han sido insertados
        if (mapaRutas.existeVertice(codigoOrigen) && mapaRutas.existeVertice(codigoDestino)) {
            mapaRutas.insertarArco(codigoOrigen, codigoDestino, distancia);
        }
    }

    public static void cargarCiudad(int codigo, String nombre, String provincia) {
        Ciudad ciudad = new Ciudad(codigo, nombre, provincia);
        boolean exito = diccionario.insertar(codigo, ciudad);
        if (exito) {

        }
    }

    public static String mostrarInfoCiudad(Comparable codigoPostal) {
        // Dado un código postal de una ciudad, mostrar toda su información
        return (diccionario.obtenerDato(codigoPostal).toString());
    }

    public static Lista mostrarCiudadesPrefijo(int prefijo) {
        return diccionario.ciudadesPrefijo(prefijo);
    }

    public static Lista solicitudesPosibles(int codigoO,int codigoD,double cantidad){
        Lista nueva= new Lista();
        if (solicitudes.verificarEspacioDisponible(codigoO, codigoD, cantidad)) {
            nueva=(mapaRutas.ciudadesIntermedias(codigoO, codigoD));
        }else{
            //si devuelve vacio no hay espacio disponible en el camion
        }
        return nueva;
    }

    public static boolean caminoPerfecto(Lista ciudades, double metrosCubicos) {
        boolean exito = false;
        int i = 2;
        while (i <= ciudades.longitud() && !exito) {
            Ciudad origen = (Ciudad) ciudades.recuperar(1);  // Ciudad origen
            int codigoO = origen.getCodigoPostal();
            Ciudad destino = (Ciudad) ciudades.recuperar(i);  // Ciudad destino
            int codigoD = destino.getCodigoPostal();
            Lista viajes = solicitudes.buscarSolicitudes(codigoO, codigoD);
            if (!viajes.esVacia()) {
                //hay al menos una solicitud
                SolicitudViaje aux= (SolicitudViaje) viajes.recuperar(1);
                metrosCubicos+= aux.getMetrosCubicos();
                if (metrosCubicos<=80.0) {
                    exito = true;//40 es el maximo de metros cubicos de capacidad del camion
                }
                ciudades.eliminar(1);  // Eliminar la primera ciudad
            }
            i++;
        }
        // Si  se encontró un camino perfecto continua de forma recursiva
        if (exito && ciudades.longitud() > 1) {
            exito = caminoPerfecto(ciudades, metrosCubicos);  // Llamada recursiva con el resto de las ciudades
        }
        return exito;
    }

}
