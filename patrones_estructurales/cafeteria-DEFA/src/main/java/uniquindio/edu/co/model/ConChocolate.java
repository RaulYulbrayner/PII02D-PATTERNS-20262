package uniquindio.edu.co.model;

public class ConChocolate extends DecoradorCafe{
    public ConChocolate(Cafe cafe) {
        super(cafe);
    }

    @Override
    public String descripcion() {
        return cafe.descripcion() + " + chocolate";
    }

    @Override
    public double costo() {
        return cafe.costo() + 1500;
    }
}
