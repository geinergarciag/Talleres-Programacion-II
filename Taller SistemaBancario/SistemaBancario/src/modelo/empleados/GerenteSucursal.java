package modelo.empleados;

import java.time.LocalDate;
import java.time.LocalDateTime;

import modelo.abstractas.Empleado;
import modelo.excepciones.DatoInvalidoException;
import modelo.excepciones.PermisoInsuficienteException;
import modelo.interfaces.Auditable;
import modelo.interfaces.Consultable;


public class GerenteSucursal extends Empleado implements Consultable, Auditable {


    private String sucursal;

    private double presupuestoAnual;

    private Empleado[] empleadosACargo;

    private int totalEmpleados;

    private LocalDateTime fechaCreacion;

    private LocalDateTime ultimaModificacion;

    private String usuarioModificacion;


    public GerenteSucursal(String id, String nombre, String apellido,
                           LocalDate fechaNacimiento, String email,
                           String legajo, LocalDate fechaContratacion, double salarioBase,
                           String sucursal, double presupuestoAnual) {

        super(id, nombre, apellido, fechaNacimiento, email,
              legajo, fechaContratacion, salarioBase);

        setSucursal(sucursal);
        setPresupuestoAnual(presupuestoAnual);

        this.empleadosACargo = new Empleado[30];
        this.totalEmpleados = 0;
        this.fechaCreacion = LocalDateTime.now();
        this.ultimaModificacion = LocalDateTime.now();
    }


    public double calcularSalario() {

        return getSalarioBase() + calcularBono();
    }


    public double calcularBono() {

        return getSalarioBase() * 0.20 + (calcularAntigüedad() * 100000);
    }


    public void aprobarCredito(Empleado empleado) {

        if (!(empleado instanceof GerenteSucursal)) {

            throw new PermisoInsuficienteException("aprobar credito");
        }

        System.out.println("Credito aprobado por: " + getNombreCompleto());
    }


    public int calcularEdad() {

        return 0;
    }


    public String obtenerTipo() {

        return "Gerente de Sucursal";
    }


    public String obtenerDocumentoIdentidad() {

        return "Legajo: " + getLegajo();
    }


    public String obtenerResumen() {

        return "Gerente: " + getNombreCompleto() +
               " | Sucursal: " + sucursal +
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


    public void setSucursal(String sucursal) {

        if (sucursal == null || sucursal.isEmpty()) {

            throw new DatoInvalidoException("sucursal", sucursal);
        }

        this.sucursal = sucursal;
    }


    public void setPresupuestoAnual(double presupuestoAnual) {

        if (presupuestoAnual < 0) {

            throw new DatoInvalidoException("presupuestoAnual", presupuestoAnual);
        }

        this.presupuestoAnual = presupuestoAnual;
    }


    public String getSucursal() { return sucursal; }

    public double getPresupuestoAnual() { return presupuestoAnual; }

    public int getTotalEmpleados() { return totalEmpleados; }

}