package TPO.SistemaMudanzas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
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
    private static FileWriter logWriter;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int entrada = 1;
        String archivo = Paths.get("src", "TPO", "SistemaMudanzas", "CargaInicial.txt").toString();
        inicializarLog();
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
                    escribirEnLog("Estado de las Estructuras luego de carga inicial: ");
                    escribirEnLog(diccionario.toString());
                    escribirEnLog(mapaRutas.toString());
                    escribirEnLog(solicitudes.toString());
                    escribirEnLog(infoClientes.toString());
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
            System.out.println("!ADVERTENCIA!Todos los nombres que se ingresen deben ir juntos sin espacios.Ejemplo, en caso de ingresar General Roca, se ingresa GeneralRoca");
            //esto es por un error leyendo los string con la clase scanner no se porque
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
                    try {
                        System.out.println("Ingrese código Postal:");
                        int codigoA = sc.nextInt();
                        System.out.println("Ingrese Nombre de la Ciudad:");
                        String nombreA = sc.next();
                        System.out.println("Ingrese Provincia:");
                        String provinciaA = sc.next();
                        Ciudad nueva=  new Ciudad(codigoA, nombreA, provinciaA);
                        boolean exito=diccionario.insertar(codigoA,nueva);
                        if (exito) {
                            System.out.println("Elemento insertado con exito");
                            escribirEnLog("Se cargo la ciudad "+nueva.toString());
                        }else{
                            System.out.println("Ha ocurrido un error");
                            escribirEnLog("No se cargo la ciudad "+nueva.toString());
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error: El formato ingresado no es válido. Asegúrese de ingresar un número para el código postal.");
                        sc.nextLine(); // Limpiar el buffer
                    } catch (NoSuchElementException e) {
                        System.out.println("Error: No se ingresó ningún valor.");
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Ingrese la ciudad que quiere eliminar:");
                        int codigoB = sc.nextInt();
                        boolean exito=diccionario.eliminar(codigoB);
                        if (exito) {
                            System.out.println("Elemento eliminado con exito");
                            escribirEnLog("Se elimino la ciudad con codigo Postal "+codigoB);
                        }else{
                            System.out.println("Ha ocurrido un error");
                            escribirEnLog("no se elimino la ciudad con codigo Postal "+codigoB);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Debe ingresar un número entero para el código postal.");
                        sc.nextLine(); // Limpiar el buffer
                    } catch (NoSuchElementException e) {
                        System.out.println("Error: No se ingresó ningún valor.");
                    } catch (Exception e) {
                        System.out.println("Error: No se pudo eliminar la ciudad. " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Ingrese el código postal de la ciudad que desea modificar:");
                        int codigoC = sc.nextInt();
                        Ciudad c = (Ciudad) diccionario.obtenerDato(codigoC);
                        if (c == null) {
                            System.out.println("Error: No se encontró ninguna ciudad con el código postal ingresado.");
                            return;
                        }
                        System.out.println("¿Qué desea modificar?");
                        System.out.println("1. Nombre de la Ciudad");
                        System.out.println("2. Provincia de la Ciudad");
                        int entradaB = sc.nextInt();
                        switch (entradaB) {
                            case 1:
                                System.out.println("Ingrese el nuevo nombre:");
                                String nombreC = sc.next();
                                c.setNombre(nombreC);
                                System.out.println("Nombre de la ciudad actualizado con éxito.");
                                escribirEnLog("Se cambio el nombre de "+c.toString());
                                break;
                            case 2:
                                System.out.println("Ingrese la nueva provincia:");
                                String provinciaC = sc.next();
                                c.setProvincia(provinciaC);
                                System.out.println("Provincia de la ciudad actualizada con éxito.");
                                escribirEnLog("Se cambio la provincia de "+c.toString());
                                break;
                            default:
                                System.out.println("Opción no válida.");
                                break;
                        }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Entrada inválida. Por favor, ingrese un valor válido.");
                            sc.nextLine(); // Limpiar el buffer en caso de error
                        } catch (NoSuchElementException e) {
                            System.out.println("Error: No se ingresó ningún valor.");
                        } catch (IllegalStateException e) {
                            System.out.println("Error: El objeto Scanner está cerrado.");
                        } catch (ClassCastException e) {
                            System.out.println("Error: El objeto obtenido no es del tipo esperado.");
                        } catch (Exception e) {
                            System.out.println("Error inesperado: " + e.getMessage());
                        }
                    break;
                default:
                    break;
            }
        }
    }

    public static void abmRutas(Scanner sc, int entrada) {
        while (entrada != 0) {
            System.out.println("<------------------Menu ABM RUTAS------------------>");
            System.out.println("1. Altas de Rutas");
            System.out.println("2. Bajas de Rutas");
            System.out.println("3. Modificaciones de Rutas");
            System.out.println("0. CERRAR");
    
            try {
                entrada = sc.nextInt();
    
                switch (entrada) {
                    case 1: // Altas de Rutas
                        try {
                            System.out.println("Ingrese la ciudad Origen de la Ruta:");
                            int codigoA = sc.nextInt();
                            System.out.println("Ingrese la ciudad Destino de la ruta:");
                            int codigoB = sc.nextInt();
                            System.out.println("Ingrese distancia en km entre las ciudades:");
                            double distancia = sc.nextDouble();
    
                            Ciudad origen = (Ciudad) diccionario.obtenerDato(codigoA);
                            Ciudad destino = (Ciudad) diccionario.obtenerDato(codigoB);
                            
                            if (origen == null || destino == null) {
                                System.out.println("Error: Una o ambas ciudades no existen en el diccionario.");
                            } else {
                                insertarRuta(codigoA, codigoB, distancia);
                                System.out.println("Ruta añadida con éxito.");
                                escribirEnLog("Se cargo la ruta con Origen "+codigoA+" ,con Destino: "+codigoB+" con distancia: "+distancia );
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Entrada inválida. Por favor, ingrese valores correctos.");
                            sc.nextLine(); // Limpiar el buffer en caso de error
                        } catch (ClassCastException e) {
                            System.out.println("Error: El dato recuperado no es del tipo esperado.");
                        } catch (Exception e) {
                            System.out.println("Error inesperado: " + e.getMessage());
                        }
                        break;
    
                    case 2: // Bajas de Rutas
                        try {
                            System.out.println("Ingrese la ciudad de Origen de la ruta que quiere eliminar:");
                            int origenB = sc.nextInt();
                            System.out.println("Ingrese la ciudad de Destino de la ruta que quiere eliminar:");
                            int destinoB = sc.nextInt();
    
                            if (!mapaRutas.eliminarArco(origenB, destinoB)) {
                                System.out.println("Error: No existe la ruta entre las ciudades especificadas.");
                            } else {
                                System.out.println("Ruta eliminada con éxito.");
                                escribirEnLog("Se elimino con exito la ruta de "+origenB+" a "+destinoB);
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Entrada inválida. Por favor, ingrese valores correctos.");
                            sc.nextLine(); // Limpiar el buffer en caso de error
                        } catch (Exception e) {
                            System.out.println("Error inesperado: " + e.getMessage());
                        }
                        break;
    
                    case 3: // Modificaciones de Rutas
                        try {
                            System.out.println("Ingrese la ciudad de Origen de la ruta que quiere modificar:");
                            int origenC = sc.nextInt();
                            System.out.println("Ingrese la ciudad de Destino de la ruta que quiere modificar:");
                            int destinoC = sc.nextInt();
                            System.out.println("Ingrese la nueva distancia en km que hay entre las ciudades:");
                            double distanciaC = sc.nextDouble();
                            if (!mapaRutas.eliminarArco(origenC, destinoC)) {
                                System.out.println("Error: No existe la ruta entre las ciudades especificadas.");
                            } else {
                                mapaRutas.insertarArco(origenC, destinoC, distanciaC);
                                System.out.println("Ruta modificada con éxito.");
                                escribirEnLog("Se modifico con exito la ruta con origen de "+origenC+" ,destino a "+destinoC);
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Entrada inválida. Por favor, ingrese valores correctos.");
                            sc.nextLine(); // Limpiar el buffer en caso de error
                        } catch (Exception e) {
                            System.out.println("Error inesperado: " + e.getMessage());
                        }
                        break;
    
                    case 0: // Cerrar
                        System.out.println("Cerrando menú ABM RUTAS...");
                        break;
    
                    default:
                        System.out.println("Opción no válida. Por favor, intente de nuevo.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Por favor, ingrese un número válido.");
                sc.nextLine(); // Limpiar el buffer en caso de error
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
    }
    
    public static void abmClientes(Scanner sc, int entrada) {
        while (entrada != 0) {
            System.out.println("<------------------Menu ABM CLIENTES------------------>");
            System.out.println("1.  Altas de Clientes");
            System.out.println("2.  Bajas de Clientes");
            System.out.println("3.  Modificaciones de Clientes");
            System.out.println("0. CERRAR");
    
            try {
                entrada = sc.nextInt();
    
                switch (entrada) {
                    case 1: // Altas de Clientes
                        try {
                            System.out.println("Ingrese el tipo de Documento del cliente nuevo:");
                            String tipoA = sc.next();
                            System.out.println("Ingrese el numero de Documento del cliente nuevo:");
                            int numeroA = sc.nextInt();
                            System.out.println("Ingrese el nombre del cliente:");
                            String nombreA = sc.next();
                            System.out.println("Ingrese el apellido del cliente:");
                            String apellidoA = sc.next();
                            System.out.println("Ingrese el telefono del cliente:");
                            int telefonoA = sc.nextInt();
                            System.out.println("Ingrese el email del cliente:");
                            String emailA = sc.next();
    
                            infoClientes.asociar(tipoA, numeroA, nombreA, apellidoA, telefonoA, emailA);
                            System.out.println("Cliente agregado con éxito.");
                            escribirEnLog("Se cargó al cliente con Tipo de Documento: " + tipoA + " y Número de Documento: " + numeroA);
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Entrada inválida. Por favor, ingrese valores correctos.");
                            sc.nextLine(); // Limpiar el buffer en caso de error
                        } catch (Exception e) {
                            System.out.println("Error inesperado: " + e.getMessage());
                        }
                        break;
    
                    case 2: // Bajas de Clientes
                        try {
                            System.out.println("Ingrese tipo de Documento del Cliente a dar de Baja:");
                            String tipoB = sc.next();
                            System.out.println("Ingrese el numero de documento del Cliente a dar de baja:");
                            int numeroB = sc.nextInt();
    
                            if (infoClientes.desacoiar(tipoB, numeroB)) {
                                System.out.println("Cliente eliminado con éxito.");
                                escribirEnLog("Se elimino al cliente con Tipo de Documento: " + tipoB + " y Número de Documento: " + numeroB);
                            } else {
                                System.out.println("Error: No se encontró un cliente con los datos especificados.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Entrada inválida. Por favor, ingrese valores correctos.");
                            sc.nextLine(); // Limpiar el buffer en caso de error
                        } catch (Exception e) {
                            System.out.println("Error inesperado: " + e.getMessage());
                        }
                        break;
    
                    case 3: // Modificaciones de Clientes
                        try {
                            System.out.println("Ingrese tipo de Documento del Cliente a modificar:");
                            String tipoC = sc.next();
                            System.out.println("Ingrese el numero de documento del Cliente a modificar:");
                            int numeroC = sc.nextInt();
                            Cliente aux = (Cliente) infoClientes.obtenerValor(tipoC, numeroC);
                            if (aux == null) {
                                System.out.println("Error: No se encontró un cliente con los datos especificados.");
                            } else {
                                System.out.println("¿Qué desea modificar del cliente " + aux.toString() + "?");
                                System.out.println("1. Nombre");
                                System.out.println("2. Apellido");
                                System.out.println("3. Teléfono");
                                System.out.println("4. Email");
                                int modificacion = sc.nextInt();
                                switch (modificacion) {
                                    case 1:
                                        System.out.println("Ingrese el nuevo nombre del cliente:");
                                        String nombreC = sc.next();
                                        aux.setNombre(nombreC);
                                        System.out.println("Nombre modificado con éxito.");
                                        break;
    
                                    case 2:
                                        System.out.println("Ingrese el nuevo apellido del cliente:");
                                        String apellidoC = sc.next();
                                        aux.setApellido(apellidoC);
                                        System.out.println("Apellido modificado con éxito.");
                                        break;
    
                                    case 3:
                                        System.out.println("Ingrese el nuevo teléfono del cliente:");
                                        int telefonoC = sc.nextInt();
                                        aux.setTelefono(telefonoC);
                                        System.out.println("Teléfono modificado con éxito.");
                                        break;
    
                                    case 4:
                                        System.out.println("Ingrese el nuevo email del cliente:");
                                        String emailC = sc.next();
                                        aux.setEmail(emailC);
                                        System.out.println("Email modificado con éxito.");
                                        break;
    
                                    default:
                                        System.out.println("Opción no válida.");
                                        break;
                                }
                            }
                            escribirEnLog("Se modificaron los datos del cliente con Tipo de Documento: " + tipoC + " y Número de Documento: " + numeroC);
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Entrada inválida. Por favor, ingrese valores correctos.");
                            sc.nextLine(); // Limpiar el buffer en caso de error
                        } catch (Exception e) {
                            System.out.println("Error inesperado: " + e.getMessage());
                        }
                        break;
    
                    case 0: // Salir
                        System.out.println("Cerrando menú ABM CLIENTES...");
                        break;
    
                    default:
                        System.out.println("Opción no válida. Por favor, intente de nuevo.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Por favor, ingrese un número válido.");
                sc.nextLine(); // Limpiar el buffer en caso de error
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
    }
    
    public static void abmSolicitudes(Scanner sc, int entrada) {
        while (entrada != 0) {
            System.out.println("<------------------Menu ABM SOLICITUDES------------------>");
            System.out.println("1. Altas de Solicitudes");
            System.out.println("2. Bajas de Solicitudes");
            System.out.println("3. Modificaciones de Solicitudes");
            System.out.println("0. CERRAR");
    
            try {
                entrada = sc.nextInt();
    
                switch (entrada) {
                    case 1: // Altas de Solicitudes
                        try {
                            System.out.println("Ingrese el código postal de origen:");
                            int codigoOrigenA = sc.nextInt();
                            System.out.println("Ingrese el código postal de destino:");
                            int codigoDestinoA = sc.nextInt();
                            System.out.println("Ingrese la fecha del viaje (formato: dd/mm/yyyy):");
                            String fechaA = sc.next();
                            System.out.println("Ingrese el tipo de documento del cliente:");
                            String tipoDocumentoA = sc.next();
                            System.out.println("Ingrese el ID del cliente:");
                            int idClienteA = sc.nextInt();
                            System.out.println("Ingrese la cantidad de bultos:");
                            int cantBultosA = sc.nextInt();
                            System.out.println("Ingrese la cantidad de metros cúbicos:");
                            double metrosCubicosA = sc.nextDouble();
                            System.out.println("Ingrese el domicilio de retiro:");
                            String domicilioRetiroA = sc.next();
                            System.out.println("Ingrese el domicilio de entrega:");
                            String domicilioEntregaA = sc.next();
                            System.out.println("¿El viaje está pagado? (true/false):");
                            boolean pagadoA = sc.nextBoolean();
    
                            solicitudes.cargarSolicitud(codigoOrigenA, codigoDestinoA, fechaA, tipoDocumentoA, idClienteA, cantBultosA, metrosCubicosA, domicilioRetiroA, domicilioEntregaA, pagadoA);
                            System.out.println("Solicitud cargada con éxito.");
                                escribirEnLog("Se cargó la solicitud desde Ciudad Origen: " + codigoOrigenA + " hasta Ciudad Destino: " + codigoDestinoA 
                + ", con Fecha: " + fechaA + ", Tipo de Documento Cliente: " + tipoDocumentoA + ", Número de Documento: " 
                + idClienteA + ", Bultos: " + cantBultosA + ", Espacio: " + metrosCubicosA + "m3, Domicilio de Retiro: " 
                + domicilioRetiroA + ", Domicilio de Entrega: " + domicilioEntregaA + ", Pagado: " + pagadoA);

                        } catch (InputMismatchException e) {
                            System.out.println("Error: Entrada inválida. Por favor, ingrese valores correctos.");
                            sc.nextLine(); // Limpiar el buffer en caso de error
                        } catch (Exception e) {
                            System.out.println("Error inesperado: " + e.getMessage());
                        }
                        break;
    
                    case 2: // Bajas de Solicitudes
                        try {
                            System.out.println("Ingrese la ciudad Origen de la solicitud:");
                            int origenB = sc.nextInt();
                            System.out.println("Ingrese la ciudad Destino de la solicitud:");
                            int destinoB = sc.nextInt();
    
                            Lista solicitudesEliminarB = solicitudes.buscarSolicitudes(origenB, destinoB);
                            if (solicitudesEliminarB == null || solicitudesEliminarB.longitud() == 0) {
                                System.out.println("No se encontraron solicitudes para las ciudades especificadas.");
                                break;
                            }
    
                            System.out.println("Las solicitudes para estas ciudades son: ");
                            System.out.println(solicitudesEliminarB.toStringSaltoLinea());
                            System.out.println("¿Cuál desea eliminar? (Ingrese la posición en la lista)");
                            int pos = sc.nextInt();
    
                            solicitudesEliminarB.eliminar(pos);
                            System.out.println("Solicitud eliminada con éxito.");
                            escribirEnLog("Se dio de baja la solicitud con origen "+origenB+" ,destino "+destinoB);
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Entrada inválida. Por favor, ingrese valores correctos.");
                            sc.nextLine(); // Limpiar el buffer en caso de error
                        } catch (Exception e) {
                            System.out.println("Error inesperado: " + e.getMessage());
                        }
                        break;
    
                    case 3: // Modificaciones de Solicitudes
                        try {
                            System.out.println("Ingrese la ciudad Origen de la solicitud:");
                            int origenC = sc.nextInt();
                            System.out.println("Ingrese la ciudad Destino de la solicitud:");
                            int destinoC = sc.nextInt();
    
                            Lista solicitudesModificar = solicitudes.buscarSolicitudes(origenC, destinoC);
                            if (solicitudesModificar == null || solicitudesModificar.longitud() == 0) {
                                System.out.println("No se encontraron solicitudes para las ciudades especificadas.");
                                break;
                            }
    
                            System.out.println("Las solicitudes para estas ciudades son: ");
                            System.out.println(solicitudesModificar.toStringSaltoLinea());
                            System.out.println("¿Cuál desea modificar? (Ingrese la posición en la lista)");
                            int posC = sc.nextInt();
    
                            SolicitudViaje aux = (SolicitudViaje) solicitudesModificar.recuperar(posC);
    
                            System.out.println("¿Qué desea modificar?");
                            System.out.println("1. Fecha");
                            System.out.println("2. Cantidad de Bultos");
                            System.out.println("3. Metros Cúbicos");
                            System.out.println("4. Domicilio de Retiro");
                            System.out.println("5. Domicilio de Entrega");
                            System.out.println("6. Estado del Pago");
    
                            int modificacion = sc.nextInt();
    
                            switch (modificacion) {
                                case 1:
                                    System.out.println("Ingrese la nueva fecha (formato: dd/mm/yyyy):");
                                    String nuevaFecha = sc.next();
                                    aux.setFecha(nuevaFecha);
                                    System.out.println("Fecha modificada con éxito.");
                                    break;
    
                                case 2:
                                    System.out.println("Ingrese la nueva cantidad de bultos:");
                                    int nuevaCantidadBultos = sc.nextInt();
                                    aux.setCantBultos(nuevaCantidadBultos);
                                    System.out.println("Cantidad de bultos modificada con éxito.");
                                    break;
    
                                case 3:
                                    System.out.println("Ingrese los nuevos metros cúbicos:");
                                    double nuevosMetrosCubicos = sc.nextDouble();
                                    aux.setMetrosCubicos(nuevosMetrosCubicos);
                                    System.out.println("Metros cúbicos modificados con éxito.");
                                    break;
    
                                case 4:
                                    System.out.println("Ingrese el nuevo domicilio de retiro:");
                                    String nuevoDomicilioRetiro = sc.next();
                                    aux.setDomicilioRetiro(nuevoDomicilioRetiro);
                                    System.out.println("Domicilio de retiro modificado con éxito.");
                                    break;
    
                                case 5:
                                    System.out.println("Ingrese el nuevo domicilio de entrega:");
                                    String nuevoDomicilioEntrega = sc.next();
                                    aux.setDomicilioEntrega(nuevoDomicilioEntrega);
                                    System.out.println("Domicilio de entrega modificado con éxito.");
                                    break;
    
                                case 6:
                                    System.out.println("Ingrese el nuevo estado del pago (true/false):");
                                    boolean nuevoEstadoPago = sc.nextBoolean();
                                    aux.setPago(nuevoEstadoPago);
                                    System.out.println("Estado del pago modificado con éxito.");
                                    break;
    
                                default:
                                    System.out.println("Opción no válida.");
                                    break;
                            }
                            escribirEnLog("Se modifico la solicitud "+aux.toString());
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Entrada inválida. Por favor, ingrese valores correctos.");
                            sc.nextLine(); // Limpiar el buffer en caso de error
                        } catch (Exception e) {
                            System.out.println("Error inesperado: " + e.getMessage());
                        }
                        break;
    
                    case 0: // Salir
                        System.out.println("Cerrando menú ABM SOLICITUDES...");
                        break;
    
                    default:
                        System.out.println("Opción no válida. Por favor, intente de nuevo.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Por favor, ingrese un número válido.");
                sc.nextLine(); // Limpiar el buffer en caso de error
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
    }
    
    public static void menuConsultas(Scanner sc, int entrada) {
        System.out.println("Bienvenido al menú de Consultas");
        System.out.println("¿Qué desea hacer a continuación?");
    
        while (entrada != 0) {
            System.out.println("<------------------Menú Consultas------------------>");
            System.out.println("Ingrese 1 para realizar consultas sobre los clientes");
            System.out.println("Ingrese 2 para realizar consultas sobre las ciudades");
            System.out.println("Ingrese 3 para realizar consultas sobre los viajes");
            System.out.println("Ingrese 4 para verificar un viaje");
            System.out.println("Ingrese 0 para cerrar");
            System.out.println("<-------------------------------------------------->");
            System.out.println("\n");
    
            try {
                entrada = sc.nextInt();
    
                switch (entrada) {
                    case 1:
                        try {
                            System.out.println("Dada una clave de un Cliente, muestra toda la información sobre él.");
                            System.out.println("Ingrese el tipo de Documento del cliente:");
                            String tipo = sc.next();
                            sc.nextLine(); // Limpiar buffer
                            System.out.println("Ingrese el número de Documento del cliente:");
                            int numero = sc.nextInt();
    
                            Object clienteInfo = infoClientes.obtenerValor(tipo, numero);
                            if (clienteInfo != null) {
                                System.out.println(clienteInfo.toString());
                            } else {
                                System.out.println("No se encontró información para el cliente especificado.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Entrada inválida. Por favor, ingrese valores correctos.");
                            sc.nextLine(); // Limpiar el buffer en caso de error
                        } catch (Exception e) {
                            System.out.println("Error inesperado: " + e.getMessage());
                        }
                        break;
    
                    case 2:
                        try {
                            menuConsultasCiudades(sc, 1);
                        } catch (Exception e) {
                            System.out.println("Error en el menú de consultas de ciudades: " + e.getMessage());
                        }
                        break;
    
                    case 3:
                        try {
                            menuConsultasMapa(sc, 1);
                        } catch (Exception e) {
                            System.out.println("Error en el menú de consultas sobre el mapa: " + e.getMessage());
                        }
                        break;
    
                    case 4:
                        try {
                            menuVerificacionViajes(sc, 1);
                        } catch (Exception e) {
                            System.out.println("Error en el menú de verificación de viajes: " + e.getMessage());
                        }
                        break;
    
                    case 0:
                        System.out.println("Cerrando el menú de Consultas...");
                        break;
    
                    default:
                        System.out.println("Opción no válida. Por favor, intente de nuevo.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Por favor, ingrese un número válido.");
                sc.nextLine(); // Limpiar el buffer en caso de error
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
    }

    public static void menuConsultasCiudades(Scanner sc, int entrada) {
        while (entrada != 0) {
            System.out.println("1. Dado un código postal de una ciudad, mostrar toda su información");
            System.out.println("2. Dado un prefijo, devolver todas las ciudades cuyo código postal comienza con dicho prefijo");
            System.out.println("Ingrese 0 para cerrar");
            try {
                entrada = sc.nextInt();
                switch (entrada) {
                    case 1:
                        System.out.println("Ingrese el código postal:");
                        int codigo = sc.nextInt();
                        System.out.println(mostrarCiudadesPrefijo(codigo));
                        break;
                    case 2:
                        System.out.println("Ingrese el prefijo:");
                        int prefijo = sc.nextInt();
                        System.out.println(mostrarCiudadesPrefijo(prefijo).toString());
                        break;
                    case 0:
                        System.out.println("Cerrando el menú de consultas de ciudades...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, intente nuevamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Por favor, ingrese un número válido.");
                sc.nextLine(); // Limpiar el buffer en caso de error
            }
        }
    }
    
    public static void menuConsultasMapa(Scanner sc, int entrada) {
        while (entrada != 0) {
            System.out.println("1. Obtener el camino más directo"); // más directo = pasa por menos ciudades
            System.out.println("2. Obtener el camino más corto en distancia");
            System.out.println("3. Obtener todos los caminos posibles entre dos ciudades pasando por una ciudad intermedia");
            System.out.println("4. Verificar si es posible viajar entre 2 ciudades con una cantidad máxima de kilómetros");
            System.out.println("Ingrese 0 para cerrar");
            
            try {
                entrada = sc.nextInt();
                switch (entrada) {
                    case 1:
                        System.out.println("Ingrese Ciudad de Origen:");
                        int origen = sc.nextInt();
                        System.out.println("Ingrese Ciudad de Destino:");
                        int destino = sc.nextInt();
                        System.out.println("El camino más directo desde " + origen + " hasta " + destino + " es:");
                        System.out.println((mapaRutas.caminoMasCorto(origen, destino)).toString());
                        break;
                    case 2:
                        System.out.println("Ingrese Ciudad de Origen:");
                        int origenA = sc.nextInt();
                        System.out.println("Ingrese Ciudad de Destino:");
                        int destinoA = sc.nextInt();
                        System.out.println("El camino más corto desde " + origenA + " hasta " + destinoA + " es:");
                        System.out.println((mapaRutas.caminoMenorDistancia(origenA, destinoA)).toString());
                        break;
                    case 3:
                        // hay que arreglar el módulo
                        break;
                    case 4:
                        System.out.println("Ingrese Ciudad de Origen:");
                        int origenC = sc.nextInt();
                        System.out.println("Ingrese Ciudad de Destino:");
                        int destinoC = sc.nextInt();
                        System.out.println("Ingrese el máximo de kilómetros:");
                        int km = sc.nextInt();
                        Lista nueva = mapaRutas.caminoMayorDistancia(origenC, destinoC, km);
                        if (nueva.esVacia()) {
                            System.out.println("No es posible viajar entre esas dos ciudades recorriendo como máximo " + km + " kilómetros");
                        } else {
                            System.out.println("Es posible viajar entre las dos ciudades siguiendo el camino:");
                            System.out.println(nueva.toString());
                        }
                        break;
                    case 0:
                        System.out.println("Cerrando el menú de consultas del mapa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, intente nuevamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Por favor, ingrese un número válido.");
                sc.nextLine(); // Limpiar el buffer en caso de error
            }
        }
    }
    
    public static void menuVerificacionViajes(Scanner sc, int entrada) {
        while (entrada != 0) {
            System.out.println("1. Dadas 2 ciudades muestra todos los pedidos y calcula el espacio necesario en el camión");
            System.out.println("2. Dadas 2 ciudades y una cantidad de metros cúbicos da todas las posibles solicitudes de viaje a ciudades intermedias");
            System.out.println("3. Dado un listado de ciudades verifica si existe un camino perfecto entre las ciudades");
            System.out.println("0. Cerrar");
            try {
                entrada = sc.nextInt();
                switch (entrada) {
                    case 1:
                        System.out.println("Ingrese la ciudad de origen (Código Postal):");
                        int origen = sc.nextInt();
                        System.out.println("Ingrese la ciudad de destino:");
                        int destino = sc.nextInt();
                        double espacio = solicitudes.calculaEspacioNecesario(origen, destino);
                        System.out.println("Para cubrir las solicitudes entre " + origen + " y " + destino + " hacen falta " + espacio + " metros cúbicos.");
                        break;
                    case 2:
                        System.out.println("Ingrese la ciudad de origen (Código Postal):");
                        int origenB = sc.nextInt();
                        System.out.println("Ingrese la ciudad de destino:");
                        int destinoB = sc.nextInt();
                        System.out.println("Ingrese los metros cúbicos:");
                        double metros = sc.nextDouble();
                        Lista caminos = solicitudesPosibles(origenB, destinoB, metros);
                        if (!caminos.esVacia()) {
                            System.out.println("Las ciudades a las que se pueden hacer viajes intermedios son:");
                            System.out.println(caminos.toString());
                        } else {
                            System.out.println("O no sobra espacio o no hay solicitudes pendientes entre las ciudades ingresadas.");
                        }
                        break;
                    case 3:
                        System.out.println("Ingrese la cantidad de ciudades que quiera chequear:");
                        int cantidad = sc.nextInt();
                        Lista ciudades = new Lista();
                        for (int i = 1; i <= cantidad; i++) {
                            System.out.println("Ingrese el código postal:");
                            int codigoPostal = sc.nextInt();
                            ciudades.insertar(new Ciudad(codigoPostal, "", ""), ciudades.longitud() + 1);
                        }
                        boolean caminoPerfecto = caminoPerfecto(ciudades, 0);
                        if (caminoPerfecto) {
                            System.out.println("Existe un camino perfecto entre las ciudades indicadas.");
                        } else {
                            System.out.println("El camino entre las ciudades no es perfecto.");
                        }
                        break;
                    case 0:
                        System.out.println("Cerrando el menú de verificación de viajes...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, intente nuevamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Por favor, ingrese un número válido.");
                sc.nextLine(); // Limpiar el buffer en caso de error
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
            try {
                entrada = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Por favor, ingrese un número válido.");
                sc.nextLine(); // Limpiar el buffer en caso de error
            }
            
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
                        cargarCliente(partes[1], Integer.parseInt(partes[2]), partes[3], partes[4], Integer.parseInt(partes[5]),partes[6]);
                        break;
                    case"S":
                        cargarSolicitud(Integer.parseInt(partes[1]), Integer.parseInt(partes[2]), partes[3],partes[4], Integer.parseInt(partes[5]), Integer.parseInt(partes[6]), Double.parseDouble(partes[7]), partes[8], partes[9], Boolean.parseBoolean(partes[10]));
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

            escribirEnLog("Se cargo la ruta con Origen "+codigoOrigen+" ,con Destino: "+codigoDestino+" con distancia: "+distancia );
        
    }

    public static void cargarCiudad(int codigo, String nombre, String provincia) {
        Ciudad ciudad = new Ciudad(codigo, nombre, provincia);
        boolean exito = diccionario.insertar(codigo, ciudad);
            if (exito) {
                escribirEnLog("Se cargo la " + ciudad.toString());
            } else {
                escribirEnLog("No se pudo cargar la " + ciudad.toString());
            }
    }

    public static void cargarCliente(String tipoDocumento, int numeroDocumento, String nombre, String apellido, int telefono,String email){
        boolean exito = infoClientes.asociar(tipoDocumento, numeroDocumento, nombre, apellido, telefono, email);
        if (exito) {
            escribirEnLog("Se cargó al cliente con Tipo de Documento: " + tipoDocumento + " y Número de Documento: " + numeroDocumento);
        } else { 
            escribirEnLog("No se pudo cargar al cliente con Tipo de Documento: " + tipoDocumento + " y Número de Documento: " + numeroDocumento);
        }
    }

    public static void cargarSolicitud(int codigoOrigen, int codigoDestino, String fecha, String tipoDocumento, int numeroDocumento, int bultos, double espacio, String domicilioRetiro,String domicilioEntrega, boolean pago){
        boolean exito = solicitudes.cargarSolicitud(codigoOrigen, codigoDestino, fecha, tipoDocumento, numeroDocumento, bultos, espacio, domicilioRetiro, domicilioEntrega, pago);
        if (exito) {
            escribirEnLog("Se cargó la solicitud desde Ciudad Origen: " + codigoOrigen + " hasta Ciudad Destino: " + codigoDestino 
                           + ", con Fecha: " + fecha + ", Tipo de Documento Cliente: " + tipoDocumento + ", Número de Documento: " 
                           + numeroDocumento + ", Bultos: " + bultos + ", Espacio: " + espacio + "m3, Domicilio de Retiro: " 
                           + domicilioRetiro + ", Domicilio de Entrega: " + domicilioEntrega + ", Pagado: " + pago);
        } else {
            escribirEnLog("No se pudo cargar la solicitud desde Ciudad Origen: " + codigoOrigen + " hasta Ciudad Destino: " + codigoDestino 
                           + ", con Fecha: " + fecha + ", Tipo de Documento Cliente: " + tipoDocumento + ", Número de Documento: " 
                           + numeroDocumento + ", Bultos: " + bultos + ", Espacio: " + espacio + "m3, Domicilio de Retiro: " 
                           + domicilioRetiro + ", Domicilio de Entrega: " + domicilioEntrega + ", Pagado: " + pago);
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

    public static void inicializarLog() {
        String rutaLog = Paths.get("src", "TPO", "SistemaMudanzas", "log.txt").toString();
        try {
            logWriter = new FileWriter(rutaLog, true);
            logWriter.write("Inicio del registro: \n");
            logWriter.flush();
        } catch (IOException ex) {
            System.err.println(ex.getMessage() + "Error al inicializar el archivo log.");
        }
    }

    public static void escribirEnLog(String mensaje) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String fechaHora = formatoFecha.format(new Date());
            logWriter.write(fechaHora + " - " + mensaje + "\n");
            logWriter.flush();
        } catch (IOException ex) {
            System.err.println(ex.getMessage() + "Error al escribir en el archivo log.");
        }
    }

    public static void finalizarLog() {
        try {
            logWriter.write("Fin del registro");
            logWriter.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage() + "Error al finalizar el archivo log.");
        }
    }


}
