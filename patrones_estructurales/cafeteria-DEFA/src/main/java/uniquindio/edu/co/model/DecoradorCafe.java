package uniquindio.edu.co.model;

public abstract class DecoradorCafe implements Cafe {

    protected final Cafe cafe;

    public DecoradorCafe(Cafe cafe){
        this.cafe = cafe;
    }

}