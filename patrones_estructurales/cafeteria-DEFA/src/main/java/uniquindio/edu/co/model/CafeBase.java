package uniquindio.edu.co.model;

public class CafeBase implements Cafe {
    @Override
    public String descripcion() {
        return "Cafe basico";
    }

    @Override
    public double costo() {
        return 2000;
    }
}
