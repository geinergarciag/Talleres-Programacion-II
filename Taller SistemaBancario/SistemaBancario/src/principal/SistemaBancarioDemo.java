package principal;

import java.time.LocalDate;

import modelo.banco.Banco;
import modelo.banco.Transaccion;
import modelo.cuentas.CuentaAhorros;
import modelo.cuentas.CuentaCorriente;
import modelo.cuentas.CuentaCredito;
import modelo.empleados.AsesorFinanciero;
import modelo.empleados.Cajero;
import modelo.empleados.GerenteSucursal;
import modelo.enums.EstadoTransaccion;
import modelo.enums.TipoDocumento;
import modelo.enums.Turno;
import modelo.excepciones.CapacidadExcedidaException;
import modelo.excepciones.ClienteNoEncontradoException;
import modelo.excepciones.CuentaBloqueadaException;
import modelo.excepciones.SaldoInsuficienteException;
import modelo.personas.ClienteEmpresarial;
import modelo.personas.ClienteNatural;


public class SistemaBancarioDemo {


    public static void main(String[] args) {

        Banco banco = new Banco("Banco ZeKo");

        System.out.println("=== SISTEMA BANCARIO ===\n");


        // Escenario 1 - Registrar clientes
        System.out.println("--- Escenario 1: Registrar clientes ---");

        ClienteNatural cliente1 = new ClienteNatural(
            "C001", "Sebastian", "Garcia",
            LocalDate.of(2000, 5, 15), "sebastian@email.com",
            TipoDocumento.CEDULA, "1234567890"
        );

        ClienteNatural cliente2 = new ClienteNatural(
            "C002", "Agus", "Lopez",
            LocalDate.of(2001, 3, 20), "agus@email.com",
            TipoDocumento.CEDULA, "0987654321"
        );

        ClienteEmpresarial cliente3 = new ClienteEmpresarial(
            "C003", "Juan", "Martinez",
            LocalDate.of(1980, 1, 10), "empresa@email.com",
            "900123456", "Empresa ZeKo SAS", "Juan Martinez"
        );

        try {

            banco.registrarCliente(cliente1);
            banco.registrarCliente(cliente2);
            banco.registrarCliente(cliente3);
            System.out.println("Clientes registrados exitosamente.\n");

        } catch (CapacidadExcedidaException e) {

            System.out.println("Error: " + e.getMessage());
        }


        // Escenario 2 - Abrir cuentas
        System.out.println("--- Escenario 2: Abrir cuentas ---");

        CuentaCorriente cuentaCorriente = new CuentaCorriente("CC001", 1000000, 500000, 15000);

        CuentaAhorros cuentaAhorros = new CuentaAhorros("CA001", 2000000, 0.05, 10);

        CuentaCredito cuentaCredito = new CuentaCredito("CR001", 5000000, 0.02, 0);

        try {

            banco.abrirCuenta("C001", cuentaCorriente);
            banco.abrirCuenta("C001", cuentaAhorros);
            banco.abrirCuenta("C002", cuentaCredito);
            System.out.println("Cuentas abiertas exitosamente.\n");

        } catch (ClienteNoEncontradoException | CapacidadExcedidaException e) {

            System.out.println("Error: " + e.getMessage());
        }


        // Escenario 3 - Deposito exitoso y cuenta bloqueada
        System.out.println("--- Escenario 3: Deposito y cuenta bloqueada ---");

        try {

            cuentaAhorros.depositar(500000);

        } catch (CuentaBloqueadaException e) {

            System.out.println("Error: " + e.getMessage());
        }

        cuentaCorriente.setBloqueada(true);

        try {

            cuentaCorriente.depositar(100000);

        } catch (CuentaBloqueadaException e) {

            System.out.println("Cuenta bloqueada capturada: " + e.getMessage() + "\n");
        }

        cuentaCorriente.setBloqueada(false);


        // Escenario 4 - Retiro exitoso y saldo insuficiente
        System.out.println("--- Escenario 4: Retiro y saldo insuficiente ---");

        try {

            cuentaAhorros.retirar(100000);

        } catch (SaldoInsuficienteException | CuentaBloqueadaException e) {

            System.out.println("Error: " + e.getMessage());
        }

        try {

            cuentaAhorros.retirar(99999999);

        } catch (SaldoInsuficienteException e) {

            System.out.println("Saldo insuficiente capturado.");
            System.out.println("Saldo actual: $" + e.getSaldoActual());
            System.out.println("Monto solicitado: $" + e.getMontoSolicitado() + "\n");

        } catch (CuentaBloqueadaException e) {

            System.out.println("Error: " + e.getMessage());
        }


        // Escenario 5 - Transferencia entre cuentas
        System.out.println("--- Escenario 5: Transferencia ---");

        Transaccion transferencia = new Transaccion(
            "T001", cuentaAhorros, cuentaCorriente, 200000, "Transferencia entre cuentas"
        );

        try {

            cuentaAhorros.retirar(200000);
            cuentaCorriente.depositar(200000);
            System.out.println("Transferencia exitosa.\n");

        } catch (SaldoInsuficienteException | CuentaBloqueadaException e) {

            System.out.println("Error en transferencia: " + e.getMessage());
        }


        // Escenario 6 - Polimorfismo en empleados
        System.out.println("--- Escenario 6: Salarios de empleados ---");

        Cajero cajero = new Cajero(
            "E001", "Carlos", "Perez",
            LocalDate.of(1990, 6, 15), "carlos@email.com",
            "LEG001", LocalDate.of(2020, 1, 10), 2000000,
            Turno.MANANA, "Sucursal Norte"
        );

        cajero.setTransaccionesDia(50);

        AsesorFinanciero asesor = new AsesorFinanciero(
            "E002", "Maria", "Gomez",
            LocalDate.of(1985, 3, 20), "maria@email.com",
            "LEG002", LocalDate.of(2018, 5, 1), 3000000,
            5000000, 4000000
        );

        GerenteSucursal gerente = new GerenteSucursal(
            "E003", "Luis", "Rodriguez",
            LocalDate.of(1975, 8, 10), "luis@email.com",
            "LEG003", LocalDate.of(2010, 3, 15), 5000000,
            "Sucursal Norte", 100000000
        );

        try {

            banco.registrarEmpleado(cajero);
            banco.registrarEmpleado(asesor);
            banco.registrarEmpleado(gerente);

        } catch (CapacidadExcedidaException e) {

            System.out.println("Error: " + e.getMessage());
        }

        modelo.abstractas.Empleado[] empleados = {cajero, asesor, gerente};

        for (modelo.abstractas.Empleado emp : empleados) {

            System.out.println(emp.obtenerTipo() + " - Salario: $" + emp.calcularSalario());
        }

        System.out.println();


        // Escenario 7 - Polimorfismo en cuentas
        System.out.println("--- Escenario 7: Intereses de cuentas ---");

        modelo.abstractas.Cuenta[] cuentas = {cuentaCorriente, cuentaAhorros, cuentaCredito};

        for (modelo.abstractas.Cuenta cuenta : cuentas) {

            System.out.println(cuenta.getTipoCuenta() + " - Interes: $" + cuenta.calcularInteres());
        }

        System.out.println();


        // Escenario 8 - Transicion invalida
        System.out.println("--- Escenario 8: Transicion invalida ---");

        try {

            transferencia.cambiarEstado(EstadoTransaccion.COMPLETADA);

        } catch (modelo.excepciones.EstadoTransaccionInvalidoException e) {

            System.out.println("Transicion invalida capturada: " + e.getMessage() + "\n");
        }


        // Escenario 9 - Permiso insuficiente
        System.out.println("--- Escenario 9: Permiso insuficiente ---");

        try {

            gerente.aprobarCredito(cajero);

        } catch (modelo.excepciones.PermisoInsuficienteException e) {

            System.out.println("Permiso insuficiente capturado: " + e.getMessage() + "\n");
        }


        // Escenario 10 - Notificaciones
        System.out.println("--- Escenario 10: Notificaciones ---");

        cliente1.notificar("Su cuenta ha sido actualizada.");

        cliente2.setAceptaNotificaciones(false);

        cliente2.notificar("Este mensaje no debe aparecer.");

        System.out.println("Cliente2 no acepta notificaciones.\n");


        // Escenario 11 - Auditoria
        System.out.println("--- Escenario 11: Auditoria ---");

        cuentaAhorros.registrarModificacion("cajero01");

        System.out.println("Ultima modificacion: " + cuentaAhorros.obtenerUltimaModificacion());

        System.out.println("Usuario modificacion: " + cuentaAhorros.obtenerUsuarioModificacion() + "\n");


        // Escenario 12 - Nomina total
        System.out.println("--- Escenario 12: Nomina total ---");

        System.out.println("Nomina total del banco: $" + banco.calcularNominaTotal());
    }
}