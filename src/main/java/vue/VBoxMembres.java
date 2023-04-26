package vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;

import static outil.LectureEcriture.lectureMembre;

public class VBoxMembres extends VBox {

    public VBoxMembres() throws IOException {
        super();
        Label labMembres = new Label("Membres");
        Label labNom = new Label("Nom");
        Label labVille = new Label("Ville");

        //Box DU HAUT
        HBox classmembres = new HBox();
        classmembres.getChildren().add(labMembres);

        //BOXS
        HBox hboxMembresEtVille = new HBox();
        VBox vboxMembres = new VBox();
        VBox vboxVilles = new VBox();
        vboxMembres.getChildren().add(labNom);
        vboxVilles.getChildren().add(labVille);
        hboxMembresEtVille.getChildren().addAll(vboxMembres, vboxVilles);

        //ESPACE DES BOXS
        VBox.setMargin(vboxMembres, new Insets(14));
        VBox.setMargin(vboxVilles, new Insets(14));
        vboxMembres.setSpacing(10);             //Espacé les noms des membres
        vboxVilles.setSpacing(10);              //Espacé les noms des villes
        hboxMembresEtVille.setSpacing(1);     //Espacé les deux box membre et ville

        //SCROLLPANE
        ScrollPane scrollPaneMembresVilles = new ScrollPane();
        scrollPaneMembresVilles.setContent(hboxMembresEtVille);
        scrollPaneMembresVilles.fitToWidthProperty().set(true);
        scrollPaneMembresVilles.fitToHeightProperty().set(true);



        //Ajout villes et nom membres dans les boxs
        TreeMap truc = lectureMembre(new File("Ressources" + File.separator + "membres_APLI.txt"));

        Set <String> key = truc.keySet();
        for (String membre: key){
            //BAR HORIZONTALE
            Line ligne =new Line(0,40,300,40);
            ligne.setStrokeWidth(2);
            ligne.setStroke(Color.BLACK);
            Line lignee =new Line(0,40,300,40);
            lignee.setStrokeWidth(2);
            lignee.setStroke(Color.BLACK);

            //Ajout des labels Membre, Ville
            Label labelMembre = new Label(membre);
            vboxMembres.getChildren().addAll(labelMembre,lignee);
            Label labelVille = new Label((String) truc.get(membre));
            vboxVilles.getChildren().addAll(labelVille,ligne);
        };

        //CSS
        labVille.setId("cssVille");
        labNom.setId("cssVille");

        //Réglage global/ Ajout à la vue les composants
        classmembres.setAlignment(Pos.BASELINE_CENTER);
        hboxMembresEtVille.setAlignment(Pos.BASELINE_CENTER);
        this.getChildren().addAll(classmembres, hboxMembresEtVille, scrollPaneMembresVilles);
        this.setSpacing(50);
    }
}