package modelo.cuentas;

import modelo.abstractas.Cuenta;
import modelo.excepciones.CuentaBloqueadaException;
import modelo.excepciones.DatoInvalidoException;
import modelo.excepciones.SaldoInsuficienteException;


public class CuentaCredito extends Cuenta {

    private double limiteCredito;

    private double tasaInteres;

    private double deudaActual;


    public CuentaCredito(String numeroCuenta, double limiteCredito,
                         double tasaInteres, double deudaActual) {

        super(numeroCuenta, 0);

        this.limiteCredito = limiteCredito;

        this.tasaInteres = tasaInteres;

        this.deudaActual = deudaActual;
    }


    public double calcularInteres() {

        return deudaActual * tasaInteres / 12;
    }

    public double getLimiteRetiro() {

        return limiteCredito;
    }

    public String getTipoCuenta() {

        return "Cuenta Credito";
    }

    public void depositar(double monto) throws CuentaBloqueadaException {

        verificarBloqueada();

        if (monto <= 0) {

            throw new DatoInvalidoException("monto", monto);
        }

        if (monto > deudaActual) {

            deudaActual = 0;

        } else {

            deudaActual = deudaActual - monto;
        }

        System.out.println("Pago de credito exitoso. Deuda actual: $" + deudaActual);
    }

    public void retirar(double monto) throws SaldoInsuficienteException, CuentaBloqueadaException {

        verificarBloqueada();

        double disponible = limiteCredito - deudaActual;

        if (monto > disponible) {

            throw new SaldoInsuficienteException(disponible, monto);
        }

        deudaActual = deudaActual + monto;

        System.out.println("Retiro de credito exitoso. Deuda actual: $" + deudaActual);
    }

    public double calcularComision(double monto) {

        return monto * 0.01;
    }

    public double consultarSaldo() {

        return limiteCredito - deudaActual;
    }


    public double getLimiteCredito() { return limiteCredito; }

    public double getTasaInteres() { return tasaInteres; }

    public double getDeudaActual() { return deudaActual; }

    public void setLimiteCredito(double limiteCredito) {

        if (limiteCredito <= 0) {

            throw new DatoInvalidoException("limiteCredito", limiteCredito);
        }

        this.limiteCredito = limiteCredito;
    }

    public void setTasaInteres(double tasaInteres) {

        if (tasaInteres < 0) {

            throw new DatoInvalidoException("tasaInteres", tasaInteres);
        }

        this.tasaInteres = tasaInteres;
    }
}