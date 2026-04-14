package modelo.personas;

import java.time.LocalDate;
import java.time.LocalDateTime;

import modelo.abstractas.Cuenta;
import modelo.abstractas.Persona;
import modelo.excepciones.CapacidadExcedidaException;
import modelo.excepciones.DatoInvalidoException;
import modelo.interfaces.Auditable;
import modelo.interfaces.Consultable;
import modelo.interfaces.Notificable;


public class ClienteEmpresarial extends Persona implements Consultable, Notificable, Auditable {


    private String nit;

    private String razonSocial;

    private String representanteLegal;

    private Cuenta[] cuentas;

    private int totalCuentas;

    private LocalDateTime fechaCreacion;

    private LocalDateTime ultimaModificacion;

    private String usuarioModificacion;

    private boolean aceptaNotificaciones;


    public ClienteEmpresarial(String id, String nombre, String apellido,
                              LocalDate fechaNacimiento, String email,
                              String nit, String razonSocial, String representanteLegal) {

        super(id, nombre, apellido, fechaNacimiento, email);

        setNit(nit);
        setRazonSocial(razonSocial);
        setRepresentanteLegal(representanteLegal);

        this.cuentas = new Cuenta[5];
        this.totalCuentas = 0;
        this.fechaCreacion = LocalDateTime.now();
        this.ultimaModificacion = LocalDateTime.now();
        this.aceptaNotificaciones = true;
    }


    public void agregarCuenta(Cuenta cuenta) throws CapacidadExcedidaException {

        if (totalCuentas >= 5) {

            throw new CapacidadExcedidaException(5);
        }

        cuentas[totalCuentas] = cuenta;

        totalCuentas++;
    }


    public Cuenta[] getCuentas() {

        Cuenta[] copia = new Cuenta[totalCuentas];

        System.arraycopy(cuentas, 0, copia, 0, totalCuentas);

        return copia;
    }


    public int calcularEdad() {

        return 0;
    }


    public String obtenerTipo() {

        return "Cliente Empresarial";
    }


    public String obtenerDocumentoIdentidad() {

        return "NIT: " + nit;
    }


    public String obtenerResumen() {

        return "Empresa: " + razonSocial +
               " | NIT: " + nit +
               " | Representante: " + representanteLegal +
               " | Cuentas: " + totalCuentas;
    }


    public boolean estaActivo() {

        return true;
    }


    public void notificar(String mensaje) {

        if (aceptaNotificaciones) {

            System.out.println("Notificacion para " + razonSocial + ": " + mensaje);
        }
    }


    public String obtenerContacto() {

        return getEmail();
    }


    public boolean aceptaNotificaciones() {

        return aceptaNotificaciones;
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


    public void setAceptaNotificaciones(boolean aceptaNotificaciones) {

        this.aceptaNotificaciones = aceptaNotificaciones;
    }


    public void setNit(String nit) {

        if (nit == null || nit.isEmpty()) {

            throw new DatoInvalidoException("nit", nit);
        }

        this.nit = nit;
    }


    public void setRazonSocial(String razonSocial) {

        if (razonSocial == null || razonSocial.isEmpty()) {

            throw new DatoInvalidoException("razonSocial", razonSocial);
        }

        this.razonSocial = razonSocial;
    }


    public void setRepresentanteLegal(String representanteLegal) {

        if (representanteLegal == null || representanteLegal.isEmpty()) {

            throw new DatoInvalidoException("representanteLegal", representanteLegal);
        }

        this.representanteLegal = representanteLegal;
    }


    public String getNit() { return nit; }

    public String getRazonSocial() { return razonSocial; }

    public String getRepresentanteLegal() { return representanteLegal; }

}