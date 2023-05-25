package lineales.dinamicas;

public class Pila {
/*****AUTORES******
-Matias Tartaglia, Legajo FAI-3310
-Ernesto Lopez, Legajo FAI-1990
*/
    private Nodo tope;

    public Pila() {
        this.tope = null;
    }

    public boolean apilar(Object nuevoElem) {
        //crea un nuevo nodo delante de la anituga cabecera
        Nodo nuevo = new Nodo(nuevoElem, this.tope);
        //Actualiza el tope para que apunte al nodo nuevo
        this.tope = nuevo;
        //devuelve true porque la pila nunca se llena
        return true;
    }

    public boolean desapilar() {
        boolean exito = true;
        if (tope == null) {
            exito = false;
        } else {
            this.tope = this.tope.getEnlace();
        }
        return exito;
    }
    public boolean esVacia(){
        boolean pilaVacia=false;
        if(this.tope==null){
            pilaVacia=true;
        }
        return pilaVacia;
    }

    public Object obtenerTope() {
        Object obj=null;
        if(this.tope!=null){
            obj=tope.getElem();
        }
        return obj;
    }

    public void vaciar() {
        this.tope = null;
    }

    public Pila clone(){
        Pila clon = new Pila();
        clonar(clon,this.tope);
        return clon;
    }
    public void clonar(Pila clon, Nodo otroEnlace){
        //Metodo recursivo para clonar Nodo por nodo
        if(otroEnlace!=null){
            // Condicion de Salida: Que llegemos al ultimo nodo (cabecera)
            clonar(clon,otroEnlace.getEnlace());
            //Se ejecuta recursivamente hasta el ultimo nodo
            clon.tope= new Nodo(otroEnlace.getElem(),clon.tope);
            //Al tope de la Pila Clon le asigna el elemento del Nodo y el puntero del tope actual
        }
    }
    public String toString () {
        String cadena;
        cadena = "]";
        Nodo nodoTemp = this.tope;
        while (nodoTemp !=null) {
            cadena = nodoTemp.getElem() + "|" + cadena  ; 
            nodoTemp = nodoTemp.getEnlace();
        }
        cadena = "[|"+cadena;
        return cadena;
      }
      
    
    public boolean equals(Pila p){
        boolean rta=true;
        Nodo aux=this.tope;
        Nodo aux2=p.tope;
        while(aux!=null && aux2!=null){
            if(!(aux2.getElem().equals(aux.getElem()))){
                rta=false;
            }
            aux=aux.getEnlace();
            aux2=aux2.getEnlace();
        }
        return rta;
    }
}
