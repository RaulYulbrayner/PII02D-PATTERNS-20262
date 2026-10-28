package uniquindio.edu.co;

import uniquindio.edu.co.model.*;
import uniquindio.edu.co.services.CafeteriaFacade;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Cafe cafe = new CafeBase();
        cafe = new ConLeche(cafe);
        cafe = new ConChocolate(cafe);

        /*
        System.out.println(cafe.descripcion());
        System.out.println(cafe.costo());
        */

        CafeteriaFacade cafeteriaFacade = new CafeteriaFacade();
        cafeteriaFacade.comprarCafe(cafe);

    }
}