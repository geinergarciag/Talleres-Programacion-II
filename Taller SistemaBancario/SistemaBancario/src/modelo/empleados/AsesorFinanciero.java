package modelo.empleados;

import java.time.LocalDate;
import java.time.LocalDateTime;

import modelo.abstractas.Empleado;
import modelo.excepciones.DatoInvalidoException;
import modelo.interfaces.Auditable;
import modelo.interfaces.Consultable;


public class AsesorFinanciero extends Empleado implements Consultable, Auditable {


    private double comisionBase;

    private double metasMensuales;

    private Object[] clientesAsignados;

    private int totalClientes;

    private LocalDateTime fechaCreacion;

    private LocalDateTime ultimaModificacion;

    private String usuarioModificacion;


    public AsesorFinanciero(String id, String nombre, String apellido,
                            LocalDate fechaNacimiento, String email,
                            String legajo, LocalDate fechaContratacion, double salarioBase,
                            double comisionBase, double metasMensuales) {

        super(id, nombre, apellido, fechaNacimiento, email,
              legajo, fechaContratacion, salarioBase);

        setComisionBase(comisionBase);
        setMetasMensuales(metasMensuales);

        this.clientesAsignados = new Object[20];
        this.totalClientes = 0;
        this.fechaCreacion = LocalDateTime.now();
        this.ultimaModificacion = LocalDateTime.now();
    }


    public double calcularSalario() {

        return getSalarioBase() + calcularBono();
    }


    public double calcularBono() {

        if (comisionBase > metasMensuales) {

            return comisionBase * 0.10;
        }

        return 0;
    }


    public int calcularEdad() {

        return 0;
    }


    public String obtenerTipo() {

        return "Asesor Financiero";
    }


    public String obtenerDocumentoIdentidad() {

        return "Legajo: " + getLegajo();
    }


    public String obtenerResumen() {

        return "Asesor: " + getNombreCompleto() +
               " | Clientes: " + totalClientes +
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


    public void setComisionBase(double comisionBase) {

        if (comisionBase < 0) {

            throw new DatoInvalidoException("comisionBase", comisionBase);
        }

        this.comisionBase = comisionBase;
    }


    public void setMetasMensuales(double metasMensuales) {

        if (metasMensuales < 0) {

            throw new DatoInvalidoException("metasMensuales", metasMensuales);
        }

        this.metasMensuales = metasMensuales;
    }


    public double getComisionBase() { return comisionBase; }

    public double getMetasMensuales() { return metasMensuales; }

    public int getTotalClientes() { return totalClientes; }

}