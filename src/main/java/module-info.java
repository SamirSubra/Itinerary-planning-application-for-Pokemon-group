module com.example.pokemon {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;

    opens com.example.pokemon to javafx.fxml;
    exports vue;
    exports outil;
    exports modele;
    exports controleur;

}