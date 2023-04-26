package controleur;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import modele.Scenario;
import outil.LectureEcriture;
import vue.VBoxAjoutScenario;
import vue.VBoxPrincipale;

import java.io.File;
import java.io.IOException;

import static outil.LectureEcriture.ecritureScenario;

public class Controleur implements EventHandler {
    Scenario scenario = new Scenario();

    public void handle(Event event) {

// la source de event est le bouton "Ajouter"
        if (event.getSource() == VBoxAjoutScenario.getButtonAjout()) {                  //lorsque l'on clic sur le boutonajout
            String vendeur = VBoxAjoutScenario.getVendeur();                            //On prend dans les combobox vendeur et acheteur
            String acheteur = VBoxAjoutScenario.getAcheteur();
            if (vendeur == "Vendeur" || acheteur == "Acheteur") {                       //Si rien n'a été ajouter on retourne un message
                System.out.println("Veuillez choisir un vendeur et un acheteur");
            } else {
                scenario.ajoutVendeurAcheteur(vendeur, acheteur);                       //Sinon on fait appelle à la méthode dans lectureecriture pour ajouter un vendeur et acheteur
                System.out.println(scenario.toString());                                //On va afficher la méthode tostring de scenario
            }
        }

        //On a interragi la vue VBoxAjoutScenario en recuperant acheteur et vendeur avec le modele scenario et utilisé sa methode d'ajout des vendeurs acheteurs

// la source de event est le bouton "Enregistrer"
        if (event.getSource() == VBoxAjoutScenario.getButtonEnr()) {                    //lorsque l'on clic sur le boutonEnrengistrer
            String titre = VBoxAjoutScenario.getTitre();                                //On recupère le titre dans le textfield
            File f = new File("Ressources" + File.separator + titre + ".txt"); //On creer un nouveau fichier texte
            if (scenario.getAcheteurs().isEmpty()) {                                    //Si liste acheteur de scenario vide on affiche un message
                System.out.println("Le scenario est vide");
            } else if (f.isFile())                                                      //Si le nom de fichier existe déjà on affiche un message
                System.out.println("Veuillez changer de nom de fichier");
            else {
                try {
                    ecritureScenario("Ressources" + File.separator + titre + ".txt", scenario);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Scénario enregistré au nom de : " + titre);
            }
        }
        //On a interragi la vue VBoxAjoutScenario en récuperant le textfield avec le modele lectureecriture et utilisé sa méthode pour ecrire un scénario


    }
}
