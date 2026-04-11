package modelo.excepciones;

public class PermisoInsuficienteException extends BancoRuntimeException {

    public PermisoInsuficienteException(String accion) {
        super("Permiso insuficiente para realizar la acción: " + accion);
    }
}