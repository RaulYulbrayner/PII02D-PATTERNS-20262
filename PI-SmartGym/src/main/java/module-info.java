module co.edu.uniquindio.poo.pismartgym {
    requires javafx.controls;
    requires javafx.fxml;

    exports co.edu.uniquindio.poo.pismartgym;
    exports co.edu.uniquindio.poo.pismartgym.model;
    exports co.edu.uniquindio.poo.pismartgym.controller;
    exports co.edu.uniquindio.poo.pismartgym.viewController;
    exports co.edu.uniquindio.poo.pismartgym.model.factory;

    opens co.edu.uniquindio.poo.pismartgym to javafx.fxml;

    opens co.edu.uniquindio.poo.pismartgym.viewController to javafx.fxml;

}