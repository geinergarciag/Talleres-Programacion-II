package modelo.banco;

import java.time.LocalDateTime;

import modelo.abstractas.Cuenta;
import modelo.abstractas.Empleado;
import modelo.excepciones.CapacidadExcedidaException;
import modelo.excepciones.ClienteNoEncontradoException;
import modelo.interfaces.Auditable;
import modelo.personas.ClienteEmpresarial;
import modelo.personas.ClienteNatural;


public class Banco implements Auditable {


    private String nombre;

    private Empleado[] empleados;

    private int totalEmpleados;

    private Object[] clientes;

    private int totalClientes;

    private Cuenta[] cuentas;

    private int totalCuentas;

    private LocalDateTime fechaCreacion;

    private LocalDateTime ultimaModificacion;

    private String usuarioModificacion;


    public Banco(String nombre) {

        this.nombre = nombre;

        this.empleados = new Empleado[50];
        this.totalEmpleados = 0;

        this.clientes = new Object[200];
        this.totalClientes = 0;

        this.cuentas = new Cuenta[500];
        this.totalCuentas = 0;

        this.fechaCreacion = LocalDateTime.now();
        this.ultimaModificacion = LocalDateTime.now();
    }


    public void registrarCliente(Object cliente) throws CapacidadExcedidaException {

        if (totalClientes >= 200) {

            throw new CapacidadExcedidaException(200);
        }

        clientes[totalClientes] = cliente;

        totalClientes++;

        System.out.println("Cliente registrado exitosamente.");
    }


    public void registrarEmpleado(Empleado empleado) throws CapacidadExcedidaException {

        if (totalEmpleados >= 50) {

            throw new CapacidadExcedidaException(50);
        }

        empleados[totalEmpleados] = empleado;

        totalEmpleados++;

        System.out.println("Empleado registrado exitosamente.");
    }


    public void abrirCuenta(String idCliente, Cuenta cuenta)
            throws ClienteNoEncontradoException, CapacidadExcedidaException {

        Object cliente = buscarCliente(idCliente);

        if (totalCuentas >= 500) {

            throw new CapacidadExcedidaException(500);
        }

        if (cliente instanceof ClienteNatural) {

            ((ClienteNatural) cliente).agregarCuenta(cuenta);

        } else if (cliente instanceof ClienteEmpresarial) {

            ((ClienteEmpresarial) cliente).agregarCuenta(cuenta);
        }

        cuentas[totalCuentas] = cuenta;

        totalCuentas++;

        System.out.println("Cuenta abierta exitosamente.");
    }


    public Object buscarCliente(String id) throws ClienteNoEncontradoException {

        for (int i = 0; i < totalClientes; i++) {

            if (clientes[i] instanceof ClienteNatural) {

                ClienteNatural cn = (ClienteNatural) clientes[i];

                if (cn.getId().equals(id)) {

                    return cn;
                }

            } else if (clientes[i] instanceof ClienteEmpresarial) {

                ClienteEmpresarial ce = (ClienteEmpresarial) clientes[i];

                if (ce.getId().equals(id)) {

                    return ce;
                }
            }
        }

        throw new ClienteNoEncontradoException(id);
    }


    public double calcularNominaTotal() {

        double total = 0;

        for (int i = 0; i < totalEmpleados; i++) {

            total += empleados[i].calcularSalario();
        }

        return total;
    }


    public void calcularInteresesMensuales() {

        for (int i = 0; i < totalCuentas; i++) {

            System.out.println("Cuenta: " + cuentas[i].getNumeroCuenta() +
                               " | Interes: $" + cuentas[i].calcularInteres());
        }
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


    public String getNombre() { return nombre; }

    public int getTotalClientes() { return totalClientes; }

    public int getTotalEmpleados() { return totalEmpleados; }

    public int getTotalCuentas() { return totalCuentas; }

}