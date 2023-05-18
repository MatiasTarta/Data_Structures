package lineales.estaticas;
public class Pila {
/*****AUTORES
-Matias Tartaglia, Legajo FAI-3310
-Ernesto Lopez, Legajo FAI-1990
*/
    private int tamanio = 10;
    private int tope;
    private Object[] arrPila;

    public Pila() {
        this.arrPila = new Object[tamanio];
        this.tope = -1;
    }

    public boolean apilar(Object nuevoElem) {
        boolean exito;
        if (this.tope + 1 >= this.tamanio) {
            exito = false;
            //pila llena
        } else {
            //la puso
            this.tope++;
            this.arrPila[tope] = nuevoElem;
            exito = true;
        }
        return exito;
    }

    public boolean desapilar() {
        boolean exito;

        if (this.tope < 0) {
            exito = false;
        } else {
            this.arrPila[tope] = null;
            tope--;
            exito = true;
        }

        return exito;
    }

    public Object obtenerTope() {
        Object nuevoElem;
        if (this.esVacia()) {
            nuevoElem = null;
        } else {
            nuevoElem = this.arrPila[tope];
        }
        return nuevoElem;
    }

    public boolean esVacia() {
        boolean noTieneElem;
        noTieneElem = this.tope < 0;
        return noTieneElem;
    }
    
    public void vaciar(){
        while(this.tope>=0){
            this.arrPila[tope]=null;
            this.tope--;
        }
    }
    
    public Pila clone(){
       Pila nuevaPila = new Pila();
       
       for(int i=0; i<=this.tope;i++){
           nuevaPila.arrPila[i]=this.arrPila[i];
       }
       nuevaPila.tope=this.tope;
       
       return nuevaPila;
    }

    public String toString() {
        String cadena="(|";
        int i=0;
        while(i<=this.tope){
            cadena= cadena+this.arrPila[i]+"|";
            i++;
        }
        cadena=cadena+"|)";
        return cadena;
    }
} 

