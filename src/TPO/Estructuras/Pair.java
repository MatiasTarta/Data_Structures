package TPO.Estructuras;

import java.util.Objects;

public class Pair {
    private final int codigoPostalOrigen;
    private final int codigoPostalDestino;

    public Pair(int codigoPostalOrigen, int codigoPostalDestino) {
        this.codigoPostalOrigen = codigoPostalOrigen;
        this.codigoPostalDestino = codigoPostalDestino;
    }

    public int getCodigoPostalOrigen() {
        return codigoPostalOrigen;
    }

    public int getCodigoPostalDestino() {
        return codigoPostalDestino;
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return codigoPostalOrigen == pair.codigoPostalOrigen &&
               codigoPostalDestino == pair.codigoPostalDestino;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoPostalOrigen, codigoPostalDestino);
    }
}
