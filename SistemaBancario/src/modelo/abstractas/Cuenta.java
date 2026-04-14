package modelo.abstractas;

import java.time.LocalDateTime;

import modelo.banco.Transaccion;
import modelo.excepciones.CapacidadExcedidaException;
import modelo.excepciones.CuentaBloqueadaException;
import modelo.excepciones.DatoInvalidoException;
import modelo.interfaces.Auditable;


public abstract class Cuenta implements Auditable {


    private String numeroCuenta;

    private double saldo;

    private boolean bloqueada;

    private LocalDateTime fechaCreacion;

    private LocalDateTime ultimaModificacion;

    private String usuarioModificacion;

    private Transaccion[] historial;

    private int totalTransacciones;


    public Cuenta(String numeroCuenta, double saldoInicial) {

        setNumeroCuenta(numeroCuenta);

        this.saldo = saldoInicial;

        this.bloqueada = false;

        this.fechaCreacion = LocalDateTime.now();

        this.ultimaModificacion = LocalDateTime.now();

        this.historial = new Transaccion[20];

        this.totalTransacciones = 0;
    }


    public abstract double calcularInteres();

    public abstract double getLimiteRetiro();

    public abstract String getTipoCuenta();


    public void verificarBloqueada() throws CuentaBloqueadaException {

        if (bloqueada) {

            throw new CuentaBloqueadaException(numeroCuenta);
        }
    }


    public void agregarAlHistorial(Transaccion t) throws CapacidadExcedidaException {

        if (totalTransacciones >= 20) {

            throw new CapacidadExcedidaException(20);
        }

        historial[totalTransacciones] = t;

        totalTransacciones++;
    }


    public Transaccion[] getHistorial() {

        Transaccion[] copia = new Transaccion[totalTransacciones];

        System.arraycopy(historial, 0, copia, 0, totalTransacciones);

        return copia;
    }


    public LocalDateTime obtenerFechaCreacion() {

        return fechaCreacion;
    }


    public LocalDateTime obtenerUltimaModificacion() {

        return ultimaModificacion;
    }


    public String obtenerUsuarioModificacion() {

        return usuarioModificacion;
    }


    public void registrarModificacion(String usuario) {

        this.usuarioModificacion = usuario;

        this.ultimaModificacion = LocalDateTime.now();
    }


    public String getNumeroCuenta() { return numeroCuenta; }

    public double getSaldo() { return saldo; }

    public boolean isBloqueada() { return bloqueada; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public LocalDateTime getUltimaModificacion() { return ultimaModificacion; }

    public String getUsuarioModificacion() { return usuarioModificacion; }


    public void setSaldo(double saldo) { this.saldo = saldo; }

    public void setBloqueada(boolean bloqueada) { this.bloqueada = bloqueada; }


    public void setNumeroCuenta(String numeroCuenta) {

        if (numeroCuenta == null || numeroCuenta.isEmpty()) {

            throw new DatoInvalidoException("numeroCuenta", numeroCuenta);
        }

        this.numeroCuenta = numeroCuenta;
    }
}
