package uniquindio.edu.co.model.chainOfResponsibility;

import uniquindio.edu.co.model.Cajero;

public abstract class Validador {

    protected Validador siguiente;

    public void setSiguiente(Validador siguiente){
        this.siguiente = siguiente;
    }

    public boolean validar(double monto, Cajero cajero){
        boolean resultado = validarActual(monto, cajero);
        if(resultado && siguiente != null){
            resultado = siguiente.validar(monto, cajero);
        }
        return resultado;
    }

    protected abstract boolean validarActual(double monto, Cajero cajero);

}
