package uniquindio.edu.co.model;

import uniquindio.edu.co.model.chainOfResponsibility.Validador;
import uniquindio.edu.co.model.chainOfResponsibility.ValidarEfectivo;
import uniquindio.edu.co.model.chainOfResponsibility.ValidarPin;
import uniquindio.edu.co.model.chainOfResponsibility.ValidarSaldo;
import uniquindio.edu.co.model.state.EstadoBloqueado;
import uniquindio.edu.co.model.state.EstadoCajero;
import uniquindio.edu.co.model.state.EstadoConTarjeta;
import uniquindio.edu.co.model.state.EstadoSinTarjeta;

public class Cajero {

    private EstadoCajero estado;
    private Validador cadena;

    private double saldo = 500000;
    private double efectivo = 1000000;
    private boolean pinCorrecto = false;

    public Cajero(){
        //patron state
        estado = new EstadoSinTarjeta();

        //patron chain
        ValidarPin pin = new ValidarPin();
        ValidarSaldo saldo = new ValidarSaldo();
        ValidarEfectivo efectivo = new ValidarEfectivo();
        pin.setSiguiente(saldo);
        saldo.setSiguiente(efectivo);
        cadena = pin;
    }

    public void insertarTarjeta(){
        if(estado instanceof EstadoSinTarjeta){
            estado = new EstadoConTarjeta();
            pinCorrecto = false;
            System.out.println("Tarjeta insertada");
        }
    }

    public void ingresarPin(String pin){
        if(estado instanceof EstadoConTarjeta){
            pinCorrecto = "12345".equals(pin);
            if(pinCorrecto){
                System.out.println("Pin correcto");
            }else{
                System.out.println("Pin incorrecto");
            }
        }else{
            System.out.println("Debe insertar la tarjeta");
        }
    }

    public void bloquear(){
        estado = new EstadoBloqueado();
        pinCorrecto = false;
    }

    public void retirar(double monto){
        estado.retirarDinero(this, monto);
    }

    public void validarRetiro(double monto){
        if(cadena.validar(monto,this)){
            saldo -= monto;
            efectivo -= monto;
            System.out.println("Retiro exitoso: $" + monto);
        }
    }

    public EstadoCajero getEstado() {
        return estado;
    }

    public void setEstado(EstadoCajero estado) {
        this.estado = estado;
    }

    public Validador getCadena() {
        return cadena;
    }

    public void setCadena(Validador cadena) {
        this.cadena = cadena;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(double efectivo) {
        this.efectivo = efectivo;
    }

    public boolean isPinCorrecto() {
        return pinCorrecto;
    }

    public void setPinCorrecto(boolean pinCorrecto) {
        this.pinCorrecto = pinCorrecto;
    }
}
