package modelo.empleados;

import java.time.LocalDate;
import java.time.LocalDateTime;

import modelo.abstractas.Empleado;
import modelo.enums.Turno;
import modelo.excepciones.DatoInvalidoException;
import modelo.interfaces.Auditable;
import modelo.interfaces.Consultable;


public class Cajero extends Empleado implements Consultable, Auditable {


    private Turno turno;

    private String sucursalAsignada;

    private int transaccionesDia;

    private LocalDateTime fechaCreacion;

    private LocalDateTime ultimaModificacion;

    private String usuarioModificacion;


    public Cajero(String id, String nombre, String apellido,
                  LocalDate fechaNacimiento, String email,
                  String legajo, LocalDate fechaContratacion, double salarioBase,
                  Turno turno, String sucursalAsignada) {

        super(id, nombre, apellido, fechaNacimiento, email,
              legajo, fechaContratacion, salarioBase);

        setTurno(turno);
        setSucursalAsignada(sucursalAsignada);

        this.transaccionesDia = 0;
        this.fechaCreacion = LocalDateTime.now();
        this.ultimaModificacion = LocalDateTime.now();
    }


    public double calcularSalario() {

        return getSalarioBase() + calcularBono();
    }


    public double calcularBono() {

        return transaccionesDia * 2000;
    }


    public int calcularEdad() {

        return 0;
    }


    public String obtenerTipo() {

        return "Cajero";
    }


    public String obtenerDocumentoIdentidad() {

        return "Legajo: " + getLegajo();
    }


    public String obtenerResumen() {

        return "Cajero: " + getNombreCompleto() +
               " | Turno: " + turno +
               " | Sucursal: " + sucursalAsignada +
               " | Salario: $" + calcularSalario();
    }


    public boolean estaActivo() {

        return isActivo();
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


    public void setTurno(Turno turno) {

        if (turno == null) {

            throw new DatoInvalidoException("turno", turno);
        }

        this.turno = turno;
    }


    public void setSucursalAsignada(String sucursalAsignada) {

        if (sucursalAsignada == null || sucursalAsignada.isEmpty()) {

            throw new DatoInvalidoException("sucursalAsignada", sucursalAsignada);
        }

        this.sucursalAsignada = sucursalAsignada;
    }


    public void setTransaccionesDia(int transaccionesDia) {

        this.transaccionesDia = transaccionesDia;
    }


    public Turno getTurno() { return turno; }

    public String getSucursalAsignada() { return sucursalAsignada; }

    public int getTransaccionesDia() { return transaccionesDia; }

}