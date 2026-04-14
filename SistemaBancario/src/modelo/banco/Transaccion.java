package modelo.banco;

import java.time.LocalDateTime;

import modelo.abstractas.Cuenta;
import modelo.enums.EstadoTransaccion;
import modelo.excepciones.EstadoTransaccionInvalidoException;


public class Transaccion {


    private String id;

    private Cuenta cuentaOrigen;

    private Cuenta cuentaDestino;

    private double monto;

    private EstadoTransaccion estado;

    private LocalDateTime fecha;

    private String descripcion;


    public Transaccion(String id, Cuenta cuentaOrigen, Cuenta cuentaDestino,
                       double monto, String descripcion) {

        this.id = id;

        this.cuentaOrigen = cuentaOrigen;

        this.cuentaDestino = cuentaDestino;

        this.monto = monto;

        this.descripcion = descripcion;

        this.estado = EstadoTransaccion.PENDIENTE;

        this.fecha = LocalDateTime.now();
    }


    public void cambiarEstado(EstadoTransaccion nuevo) {

        boolean valido = false;

        if (estado == EstadoTransaccion.PENDIENTE &&
            (nuevo == EstadoTransaccion.PROCESANDO || nuevo == EstadoTransaccion.RECHAZADA)) {
            valido = true;
        }

        if (estado == EstadoTransaccion.PROCESANDO &&
            (nuevo == EstadoTransaccion.COMPLETADA || nuevo == EstadoTransaccion.RECHAZADA)) {
            valido = true;
        }

        if (estado == EstadoTransaccion.COMPLETADA && nuevo == EstadoTransaccion.REVERTIDA) {
            valido = true;
        }

        if (!valido) {
            throw new EstadoTransaccionInvalidoException(estado.toString(), nuevo.toString());
        }

        this.estado = nuevo;
    }


    public String generarComprobante() {

        return "Comprobante: " + id +
               "\nFecha: " + fecha +
               "\nMonto: " + monto +
               "\nEstado: " + estado +
               "\nDescripcion: " + descripcion;
    }


    public String getId() { return id; }

    public Cuenta getCuentaOrigen() { return cuentaOrigen; }

    public Cuenta getCuentaDestino() { return cuentaDestino; }

    public double getMonto() { return monto; }

    public EstadoTransaccion getEstado() { return estado; }

    public LocalDateTime getFecha() { return fecha; }

    public String getDescripcion() { return descripcion; }

}