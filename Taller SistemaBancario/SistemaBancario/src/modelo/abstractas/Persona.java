package modelo.abstractas;

import java.time.LocalDate;

import modelo.excepciones.DatoInvalidoException;


public abstract class Persona {


    private String id;

    private String nombre;

    private String apellido;

    private LocalDate fechaNacimiento;

    private String email;


    public Persona(String id, String nombre, String apellido,
                   LocalDate fechaNacimiento, String email) {

        setId(id);
        setNombre(nombre);
        setApellido(apellido);
        setFechaNacimiento(fechaNacimiento);
        setEmail(email);
    }


    public abstract int calcularEdad();

    public abstract String obtenerTipo();

    public abstract String obtenerDocumentoIdentidad();


    public String getNombreCompleto() {

        return nombre + " " + apellido;
    }


    public String getId() { return id; }

    public String getNombre() { return nombre; }

    public String getApellido() { return apellido; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }

    public String getEmail() { return email; }


    public void setId(String id) {

        if (id == null || id.isEmpty()) {

            throw new DatoInvalidoException("id", id);
        }

        this.id = id;
    }


    public void setNombre(String nombre) {

        if (nombre == null || nombre.isEmpty()) {

            throw new DatoInvalidoException("nombre", nombre);
        }

        this.nombre = nombre;
    }


    public void setApellido(String apellido) {

        if (apellido == null || apellido.isEmpty()) {

            throw new DatoInvalidoException("apellido", apellido);
        }

        this.apellido = apellido;
    }


    public void setFechaNacimiento(LocalDate fechaNacimiento) {

        if (fechaNacimiento == null || fechaNacimiento.isAfter(LocalDate.now())) {

            throw new DatoInvalidoException("fechaNacimiento", fechaNacimiento);
        }

        this.fechaNacimiento = fechaNacimiento;
    }


    public void setEmail(String email) {

        if (email == null || !email.contains("@")) {

            throw new DatoInvalidoException("email", email);
        }

        this.email = email;
    }
}
