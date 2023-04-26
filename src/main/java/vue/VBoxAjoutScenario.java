package vue;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;

import static outil.LectureEcriture.lectureMembre;

public class VBoxAjoutScenario extends VBox {
    static ComboBox<String> comboMembre1 = new ComboBox<>();
    static ComboBox<String> comboMembre2 = new ComboBox<>();
    static TextField fieldTitre = new TextField();
    static Button buttonEnregistrer = new Button("Enregistrer");
    static Button buttonAjouter = new Button("Ajouter");
    Controleur controleur = new Controleur();

    public VBoxAjoutScenario() throws IOException {
        super(50);

        //ATTRIBUTION DES VALEURS PAR DEFAUT
        fieldTitre.setText("Mon_scenario");
        comboMembre1.setValue("Vendeur");
        comboMembre2.setValue("Acheteur");

        //LABEL
        Label labScenario = new Label("Scenario");
        Label labMembre1 = new Label("membre 1");
        Label labMembre2 = new Label("membre 2");
        Label labTitre = new Label("Titre");


        //BOUTONS
        Button buttonAnnuler = new Button("Annuler");

        //Affectation de l'action des boutons
        buttonAnnuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                reset();
            }
        });
        buttonAjouter.setOnAction(controleur);
        buttonEnregistrer.setOnAction(controleur);

        //HBOX et VBOX
        HBox hboxLabScenario = new HBox();
        HBox hboxMembres1 = new HBox(50);
        HBox hboxMembres2 = new HBox(50);
        VBox vboxMembres = new VBox(50);
        VBox vboxTitre = new VBox(15);
        VBox vboxScenario = new VBox();
        HBox hboxBouttons = new HBox(15);
        hboxLabScenario.getChildren().add(labScenario);

        //AJOUT DES MEMBRES DANS LES COMBOBOX
        TreeMap MapMembres = lectureMembre(new File("Ressources" + File.separator + "membres_APLI.txt"));
        Set<String> listeMembres = MapMembres.keySet();
        for (String membre:listeMembres){
            comboMembre1.getItems().add(membre);
            comboMembre2.getItems().add(membre);
        }

        //ARRANGEMENTS GRAPHIQUE
        hboxLabScenario.setAlignment(Pos.CENTER);
        vboxTitre.setAlignment(Pos.CENTER);
        hboxBouttons.setAlignment(Pos.BOTTOM_RIGHT);
        hboxLabScenario.setPadding(new Insets(30));
        vboxMembres.setPadding(new Insets(30));
        vboxTitre.setPadding(new Insets(30));
        hboxBouttons.setPadding(new Insets(20));


        //AJOUT DES ELEMENTS
        hboxMembres1.getChildren().addAll(labMembre1,comboMembre1);
        hboxMembres2.getChildren().addAll(labMembre2,comboMembre2);
        vboxMembres.getChildren().addAll(hboxMembres1,hboxMembres2,buttonAjouter);
        vboxTitre.getChildren().addAll(labTitre,fieldTitre);
        vboxScenario.getChildren().addAll(vboxMembres,vboxTitre);
        hboxBouttons.getChildren().addAll(buttonAnnuler,buttonEnregistrer);
        this.getChildren().addAll(vboxTitre,vboxMembres,hboxBouttons);
    }

    public void reset(){
        fieldTitre.setText("Mon_scenario");
        comboMembre1.setValue("Vendeur");
        comboMembre2.setValue("Acheteur");

    }

    public static String getVendeur(){
        return comboMembre1.getSelectionModel().getSelectedItem();
    }


    public static String getAcheteur(){
        return comboMembre2.getSelectionModel().getSelectedItem();
    }

    public static String getTitre(){
        return fieldTitre.getText();
    }
    public static Button getButtonEnr(){
        return buttonEnregistrer;
    }

    public static Button getButtonAjout(){
        return buttonAjouter;
    }
}

