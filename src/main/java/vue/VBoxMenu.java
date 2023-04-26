package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class VBoxMenu extends VBox {
    VBoxPrincipale vBoxPrincipale = new VBoxPrincipale();
    VBoxAjoutScenario vBoxAjoutScenario = new VBoxAjoutScenario();
    VBoxMembres vBoxMembres = new VBoxMembres();

    public VBoxMenu() throws IOException {

        //LISTE DES MENUS
        MenuBar menuBar = new MenuBar();
        Menu menuItineraire = new Menu("Itinéraires");
        Menu menuEcrScenario = new Menu("Scenario");
        Menu menuMembres = new Menu("Membres");



        //LISTE DES MENUS ITEMS
        MenuItem menuItemItineraire = new MenuItem("Afficher les itinéraires");
        MenuItem menuItemEcrScenario = new MenuItem("Ecrire un scenario");
        MenuItem menuItemMembres = new MenuItem("Afficher les membres");



        //AJOUT DES MENUS
        menuBar.getMenus().addAll(menuItineraire,menuEcrScenario,menuMembres);
        menuItineraire.getItems().add(menuItemItineraire);
        menuEcrScenario.getItems().add(menuItemEcrScenario);
        menuMembres.getItems().add(menuItemMembres);



        ///TALBEAU DES CLASSES
        Node[] components = new Node[3];
        components[0] = vBoxPrincipale;
        components[1] = vBoxAjoutScenario;
        components[2] = vBoxMembres;

        //STACKPANE
        StackPane stackPane = new StackPane(components);
        stackPane.getChildren().get(0).toFront();
        stackPane.getChildren().get(0).setVisible(false);
        stackPane.getChildren().get(1).setVisible(false);
        stackPane.getChildren().get(2).setVisible(true);

        //DONNEES DES MENUS
        menuItineraire.setUserData(2);
        menuEcrScenario.setUserData(1);
        menuMembres.setUserData(0);

        //DETERMINATION DES ACTIONS DES MENUS
        menuItemItineraire.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent evt) {
                int last = stackPane.getChildren().size() - 1;

                while (stackPane.getChildren().get(last) != components[0]) {
                    stackPane.getChildren().get(0).toFront();
                    stackPane.getChildren().get(0).setVisible(false);
                    stackPane.getChildren().get(1).setVisible(false);
                    stackPane.getChildren().get(2).setVisible(true);
                }
            }
        });

        menuItemEcrScenario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent evt) {
                int last = stackPane.getChildren().size() - 1;

                while (stackPane.getChildren().get(last) != components[1]) {
                    stackPane.getChildren().get(0).toFront();
                    stackPane.getChildren().get(0).setVisible(false);
                    stackPane.getChildren().get(1).setVisible(false);
                    stackPane.getChildren().get(2).setVisible(true);
                }
            }
        });


        menuItemMembres.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent evt) {
                int last = stackPane.getChildren().size() - 1;

                while (stackPane.getChildren().get(last) != components[2]) {
                    stackPane.getChildren().get(0).toFront();
                    stackPane.getChildren().get(0).setVisible(false);
                    stackPane.getChildren().get(1).setVisible(false);
                    stackPane.getChildren().get(2).setVisible(true);
                }
            }
        });


        this.getChildren().addAll(menuBar, stackPane);

    }
}
