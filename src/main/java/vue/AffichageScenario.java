package vue;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modele.Scenario;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;

import static outil.LectureEcriture.lectureScenario;


public class AffichageScenario extends HBox {
    private static Scenario monScenario = new Scenario();
    private static Scenario monScenario2 = new Scenario();

    public AffichageScenario() throws IOException {
        super(20);

        ////////////////////////////////////////Partie Scenario.txt////////////////////////////////////////
        monScenario = lectureScenario(new File("Ressources" + File.separator + "scenario_2_2.txt")); //lecture d'un scenario depuis le fichier Ressources
        Label labScenario = new Label(monScenario.toString());                                  //création du label qui servira à afficher les scénario graphiquement
        HBox hbox = new HBox();
        hbox.getChildren().add(labScenario);
        ScrollPane scrollpane = new ScrollPane();
        scrollpane.setContent(hbox);
        this.getChildren().addAll(scrollpane);

    }
}

