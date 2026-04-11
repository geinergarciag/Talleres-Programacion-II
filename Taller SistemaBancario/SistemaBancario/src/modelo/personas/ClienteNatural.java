package modelo.personas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import modelo.abstractas.Cuenta;
import modelo.abstractas.Persona;
import modelo.enums.TipoDocumento;
import modelo.excepciones.CapacidadExcedidaException;
import modelo.excepciones.DatoInvalidoException;
import modelo.interfaces.Auditable;
import modelo.interfaces.Consultable;
import modelo.interfaces.Notificable;


public class ClienteNatural extends Persona implements Consultable, Notificable, Auditable {


    private TipoDocumento tipoDocumento;

    private String numeroDocumento;

    private Cuenta[] cuentas;

    private int totalCuentas;

    private LocalDateTime fechaCreacion;

    private LocalDateTime ultimaModificacion;

    private String usuarioModificacion;

    private boolean aceptaNotificaciones;


    public ClienteNatural(String id, String nombre, String apellido,
                          LocalDate fechaNacimiento, String email,
                          TipoDocumento tipoDocumento, String numeroDocumento) {

        super(id, nombre, apellido, fechaNacimiento, email);

        setTipoDocumento(tipoDocumento);
        setNumeroDocumento(numeroDocumento);

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

        return Period.between(getFechaNacimiento(), LocalDate.now()).getYears();
    }


    public String obtenerTipo() {

        return "Cliente Natural";
    }


    public String obtenerDocumentoIdentidad() {

        return tipoDocumento + ": " + numeroDocumento;
    }


    public String obtenerResumen() {

        return "Cliente: " + getNombreCompleto() +
               " | Documento: " + obtenerDocumentoIdentidad() +
               " | Cuentas: " + totalCuentas;
    }


    public boolean estaActivo() {

        return true;
    }


    public void notificar(String mensaje) {

        if (aceptaNotificaciones) {

            System.out.println("Notificacion para " + getNombreCompleto() + ": " + mensaje);
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


    public void setTipoDocumento(TipoDocumento tipoDocumento) {

        if (tipoDocumento == null) {

            throw new DatoInvalidoException("tipoDocumento", tipoDocumento);
        }

        this.tipoDocumento = tipoDocumento;
    }


    public void setNumeroDocumento(String numeroDocumento) {

        if (numeroDocumento == null || numeroDocumento.isEmpty()) {

            throw new DatoInvalidoException("numeroDocumento", numeroDocumento);
        }

        this.numeroDocumento = numeroDocumento;
    }


    public TipoDocumento getTipoDocumento() { return tipoDocumento; }

    public String getNumeroDocumento() { return numeroDocumento; }

}