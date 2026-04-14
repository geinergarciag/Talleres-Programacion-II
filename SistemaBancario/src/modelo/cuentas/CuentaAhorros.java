package modelo.cuentas;

import modelo.abstractas.Cuenta;
import modelo.excepciones.CuentaBloqueadaException;
import modelo.excepciones.DatoInvalidoException;
import modelo.excepciones.SaldoInsuficienteException;


public class CuentaAhorros extends Cuenta {

    private double tasaInteres;

    private int retirosMesActual;

    private int maxRetirosMes;


    public CuentaAhorros(String numeroCuenta, double saldoInicial,
                         double tasaInteres, int maxRetirosMes) {

        super(numeroCuenta, saldoInicial);

        this.tasaInteres = tasaInteres;

        this.retirosMesActual = 0;

        this.maxRetirosMes = maxRetirosMes;
    }


    public double calcularInteres() {

        return getSaldo() * tasaInteres / 12;
    }

    public double getLimiteRetiro() {

        return 3000000;
    }

    public String getTipoCuenta() {

        return "Cuenta Ahorros";
    }

    public void depositar(double monto) throws CuentaBloqueadaException {

        verificarBloqueada();

        if (monto <= 0) {

            throw new DatoInvalidoException("monto", monto);
        }

        setSaldo(getSaldo() + monto);

        System.out.println("Deposito exitoso en ahorros. Nuevo saldo: $" + getSaldo());
    }

    public void retirar(double monto) throws SaldoInsuficienteException, CuentaBloqueadaException {

        verificarBloqueada();

        if (monto > getSaldo()) {

            throw new SaldoInsuficienteException(getSaldo(), monto);
        }

        setSaldo(getSaldo() - monto);

        retirosMesActual++;

        System.out.println("Retiro exitoso en ahorros. Nuevo saldo: $" + getSaldo());
    }

    public double calcularComision(double monto) {

        return monto * 0.005;
    }

    public double consultarSaldo() {

        return getSaldo();
    }


    public double getTasaInteres() { return tasaInteres; }

    public int getRetirosMesActual() { return retirosMesActual; }

    public int getMaxRetirosMes() { return maxRetirosMes; }

    public void setTasaInteres(double tasaInteres) {

        if (tasaInteres < 0) {

            throw new DatoInvalidoException("tasaInteres", tasaInteres);
        }

        this.tasaInteres = tasaInteres;
    }
}