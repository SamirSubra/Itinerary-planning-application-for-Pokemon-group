package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modele.Scenario;
import outil.LectureEcriture;
import java.io.File;
import java.io.IOException;
public class VBoxPrincipale extends VBox {


    Scenario scenario = new Scenario();
    ComboBox comboBoxTypeSoluce = new ComboBox();
    ComboBox comboBoxScenarios =  new ComboBox<>();
    Label labSoluce = new Label(" Distance totale : ..km");
    static Button buttonSelectionScenario = new Button("Choisir un scénario");
    Label labItineraire = new Label("Veuillez choisir un scénario                                           \n");
    Label labLigneRessources = new Label("... ");
    static Button buttonOk = new Button("Ok");
    ScrollPane scrollPaneItineraire = new ScrollPane();


    public VBoxPrincipale() throws IOException {
        super(100);
        //VBox et HBox
        VBox vboxRessources = new VBox();
        HBox hboxTypeSoluce  = new HBox(15);
        HBox hboxNord = new HBox();
        HBox hboxCentre = new HBox();
        VBox vboxSud = new VBox(50);

        //SCROLLPANE
        ScrollPane scrollPaneRessources = new ScrollPane();
        scrollPaneItineraire.setContent(labItineraire);

        //BOUTONS
        buttonOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String solution = (String) comboBoxTypeSoluce.getSelectionModel().getSelectedItem();
                System.out.println(solution);
                try {
                    setTypeSoluce(solution);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        //SELECTION
        FileChooser fileChooser = new FileChooser(); // FileChooser permets de séléctionner un fichier depuis n'importe quel répértoire
        fileChooser.setTitle("Sélectionner un scénario");
        fileChooser.setInitialDirectory(new File("Ressources"));     // Choisir un répertoire par défaut
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        Stage primaryStage = new Stage();
        buttonSelectionScenario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File file = fileChooser.showOpenDialog(primaryStage); //Affiche la fenetre permettant de selectionner le fichier scénario
                if (file != null) {
                    try {
                        scenario = setScenario(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        //AJOUT DANS LES COMBOBOX
        comboBoxTypeSoluce.getItems().addAll("Solution quelconque","Toutes les solutions possibles","Meilleure solution");
        comboBoxScenarios.getItems().addAll("Scénario 1 ","Scénario 2" ,"etc...");
        comboBoxTypeSoluce.setValue("Types de solution");

        //MENUBAR
        MenuBar menuBar = new MenuBar();
        Menu menuMembres = new Menu("Membres");
        Menu menuEcrScenario = new Menu("Ecrire un scenario");
        Menu menuItineraire = new Menu("Itinéraires");
        menuBar.getMenus().addAll(menuMembres,menuEcrScenario,menuItineraire);

        //ARRANGEMENTS GRAPHIQUES
        hboxNord.setAlignment(Pos.CENTER);
        vboxSud.setAlignment(Pos.CENTER);
        hboxTypeSoluce.setAlignment(Pos.CENTER);
        hboxNord.setPadding(new Insets(20));
        hboxCentre.setPadding(new Insets(20));
        vboxSud.setPadding(new Insets(15));
        scrollPaneItineraire.setMinHeight(50);
        scrollPaneRessources.setMinHeight(120);

        //AJOUT DES ELEMENTS
        vboxRessources.getChildren().add(labLigneRessources);
        scrollPaneRessources.setContent(vboxRessources);
        hboxNord.getChildren().addAll(labSoluce);
        hboxCentre.getChildren().addAll(scrollPaneItineraire);
        hboxTypeSoluce.getChildren().addAll(buttonSelectionScenario,comboBoxTypeSoluce,buttonOk);
        vboxSud.getChildren().addAll(scrollPaneRessources,hboxTypeSoluce);
        this.getChildren().addAll(hboxNord, hboxCentre,vboxSud);
    }


    //Selectionner un scénario à partir du bouton choisir scénario
    public Scenario setScenario(File parFile) throws IOException {
        Scenario scenario = LectureEcriture.lectureScenario(parFile);
        String [] itineraire = scenario.itineraire();
        String distanceTotal = String.valueOf(scenario.getDistanceTotale(itineraire));
        String strItineraire = "";

        for(int i=0;i<itineraire.length;i++){
            if(i== itineraire.length-1)
                strItineraire += itineraire[i] ;
            else
                strItineraire += itineraire[i] + " -> ";
        }
        labItineraire.setText(strItineraire);
        labSoluce.setText("Distance totale : " + distanceTotal+ "km");
        labLigneRessources.setText(scenario.ressources());

        return scenario;

    }
    //Changer l'itinéraire selon le type de solution souhaité
    public void setTypeSoluce(String solution) throws IOException {
        String[] itineraire = this.scenario.itineraire();
        if(solution == "Meilleure solution")
            itineraire = this.scenario.meilleur_itineraire();
        /*if (solution == " Toutes les solutions possibles" )
            itineraire = this.scenario.itineraire();*/

        String distanceTotal = String.valueOf(this.scenario.getDistanceTotale(itineraire));
        String strItineraire = "";
        for (int i = 0; i < itineraire.length; i++) {
            if (i == itineraire.length - 1)
                strItineraire += itineraire[i];
            else
                strItineraire += itineraire[i] + " -> ";
        }
        labItineraire.setText(strItineraire);
        labSoluce.setText("Distance totale : " + distanceTotal + "km");
        System.out.println(strItineraire);
    }


}
