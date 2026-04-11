package modelo.excepciones;

public class CapacidadExcedidaException extends SistemaBancarioException {

    private int capacidadMaxima;

    public CapacidadExcedidaException(int capacidadMaxima) {
        super("Se alcanzó la capacidad máxima de: " + capacidadMaxima, "CAPACIDAD-001");
        this.capacidadMaxima = capacidadMaxima;
        
    }
    

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
    
}