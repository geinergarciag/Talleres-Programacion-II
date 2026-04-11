package modelo.excepciones;

public class EstadoTransaccionInvalidoException extends BancoRuntimeException {

    public EstadoTransaccionInvalidoException(String estadoOrigen, String estadoDestino) {
        super("Transición inválida de estado: " + estadoOrigen + " -> " + estadoDestino);
    }
}