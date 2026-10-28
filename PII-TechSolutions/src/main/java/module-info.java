module co.edu.uniquindio.poo.piitechsolutions {

    requires javafx.controls;
    requires javafx.fxml;

    // Paquete principal de la aplicación.
    exports co.edu.uniquindio.poo.piitechsolutions;

    // Paquetes del modelo.
    exports co.edu.uniquindio.poo.piitechsolutions.model;
    exports co.edu.uniquindio.poo.piitechsolutions.model.factory;
    exports co.edu.uniquindio.poo.piitechsolutions.model.decorator;
    exports co.edu.uniquindio.poo.piitechsolutions.model.adapter;

    // Servicios y controlador de aplicación.
    exports co.edu.uniquindio.poo.piitechsolutions.services;
    exports co.edu.uniquindio.poo.piitechsolutions.controller;

    // Permite a FXMLLoader construir los controladores
    // e inyectar los atributos y métodos privados @FXML.
    opens co.edu.uniquindio.poo.piitechsolutions.viewController to javafx.fxml;
}
