package uniquindio.edu.co.model;

public class ConLeche extends DecoradorCafe {

    public ConLeche(Cafe cafe) {
        super(cafe);
    }

    @Override
    public String descripcion() {
        return cafe.descripcion() + " + leche";
    }

    @Override
    public double costo() {
        return cafe.costo() + 1000;
    }
}
