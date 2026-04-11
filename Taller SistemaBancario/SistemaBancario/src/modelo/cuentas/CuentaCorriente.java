package modelo.cuentas;

import modelo.abstractas.Cuenta;
import modelo.excepciones.CuentaBloqueadaException;
import modelo.excepciones.DatoInvalidoException;
import modelo.excepciones.SaldoInsuficienteException;


public class CuentaCorriente extends Cuenta {

    private double montoSobregiro;

    private double comisionMantenimiento;


    public CuentaCorriente(String numeroCuenta, double saldoInicial,
                           double montoSobregiro, double comisionMantenimiento) {

        super(numeroCuenta, saldoInicial);

        this.montoSobregiro = montoSobregiro;

        this.comisionMantenimiento = comisionMantenimiento;
    }


    public double calcularInteres() {

        return 0;
    }

    public double getLimiteRetiro() {

        return 50000000;
    }

    public String getTipoCuenta() {

        return "Cuenta Corriente";
    }

    public void depositar(double monto) throws CuentaBloqueadaException {

        verificarBloqueada();

        if (monto <= 0) {

            throw new DatoInvalidoException("monto", monto);
        }

        setSaldo(getSaldo() + monto);

        System.out.println("Deposito exitoso en corriente. Nuevo saldo: $" + getSaldo());
    }

    public void retirar(double monto) throws SaldoInsuficienteException, CuentaBloqueadaException {

        verificarBloqueada();

        double disponible = getSaldo() + montoSobregiro;

        if (monto > disponible) {

            throw new SaldoInsuficienteException(getSaldo(), monto);
        }

        setSaldo(getSaldo() - monto);

        System.out.println("Retiro exitoso en corriente. Nuevo saldo: $" + getSaldo());
    }

    public double calcularComision(double monto) {

        return comisionMantenimiento;
    }

    public double consultarSaldo() {

        return getSaldo();
    }

    public double getMontoSobregiro() { return montoSobregiro; }

    public double getComisionMantenimiento() { return comisionMantenimiento; }

    public void setMontoSobregiro(double montoSobregiro) {

        if (montoSobregiro < 0) {

            throw new DatoInvalidoException("montoSobregiro", montoSobregiro);
        }

        this.montoSobregiro = montoSobregiro;
    }

    public void setComisionMantenimiento(double comision) {

        this.comisionMantenimiento = comision;
    }
}